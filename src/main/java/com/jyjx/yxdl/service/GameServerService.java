package com.jyjx.yxdl.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jyjx.yxdl.common.Redis;
import com.jyjx.yxdl.common.ShiroRealm;
import com.jyjx.yxdl.entity.GameServer;
import com.jyjx.yxdl.mapper.GameServerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class GameServerService {

    private static Logger logger = LoggerFactory.getLogger(GameServerService.class);

    @Autowired
    public GameServerMapper gameServerMapper;

    public List<GameServer> getGameServer(int appId){

        return gameServerMapper.getGameServer(appId);

    }


    public GameServer findServerById(int serverId){

        return gameServerMapper.findServerById(serverId);

    }


    public void merge(Map<String,String> from, Map<String,String> to) {
        Redis toRedis = new Redis(to.get("host"), Integer.parseInt(to.get("port")),Integer.parseInt(to.get("index")));
        if ("PONG".equals(toRedis.pres())) {
            logger.info("To库链接正常...");
        } else {
            logger.error("To库链接失败...");
            return;
        }

        // 预处理...
        logger.info("TO预处理开始...");
        preprocessing(toRedis, to);
        logger.info("TO预处理完成...");

//        for (String f : form.split(",")) {
//            ServerConfig fc = serverConfigService.getServerConfig(Integer.parseInt(f));
            Redis formRedis = new Redis(from.get("host"),Integer.parseInt(from.get("port")), Integer.parseInt(from.get("index")));
            if ("PONG".equals(formRedis.pres())) {
                logger.info("Form库链接正常...");
            } else {
                logger.error("Form库链接失败...");
                return;
            }

            // 预处理...
            logger.info("Form预处理开始...");
            preprocessing(formRedis, from);
            logger.info("Form预处理完成...");

            this.merge(from, formRedis, to, toRedis);
//        }
    }

    private void preprocessing(Redis redis, Map<String,String> config) {
        // 删除竞技类的系统配置
        redis.hdel(String.valueOf(config.get("id")), "fleeSystemTR");
        redis.hdel(String.valueOf(config.get("id")), "servermails");
        redis.hdel(String.valueOf(config.get("id")), "arenaSystemTR");
        redis.hdel(String.valueOf(config.get("id")), "five2FiveSystem");
        redis.hdel(String.valueOf(config.get("id")), "soloSystemTR");

        // 删除几个排行榜...
        redis.del("rank/" + config.get("id") + "/GUILD_BOSS_GUILD/preday");
        redis.del("rank/" + config.get("id") + "/GUILD_BOSS_GUILD/today");
        redis.del("rank/" + config.get("id") + "/GUILD_BOSS_SINGLE/preday");
        redis.del("rank/" + config.get("id") + "/GUILD_BOSS_SINGLE/today");
        redis.del("rank/" + config.get("id") + "/FLEE");
        redis.del("rank/" + config.get("id") + "/PVP_5V5");
        redis.del("rank/" + config.get("id") + "/ARENA_SCORE");

        for (int i = 0; i < 100; i++) {
            redis.del("rank/" + config.get("id") + "/ARENA_SCOREALL-" + i);
            redis.del("rank/" + config.get("id") + "/SOLO_SCORE-" + i);
        }

        // 未合服才更名...
//        if (ServerService.getSidList(config.getId()).size() == 1) {
            {// 修正公会名称
                Map<String, String> data = redis.hgetAll("guild");
                for (String k : data.keySet()) {
                    JSONObject object = JSON.parseObject(data.get(k));
//                    object.put("name", "S" + (Integer.parseInt(config.get("id")) - 10000) + "." + object.getString("name"));
                    object.put("name",object.getString("name"));
                    data.put(k, object.toJSONString());
                    logger.info("修正公会名称：{}", object.getString("name"));
                }
                if(data.size() > 0 ){
                    redis.hmset("guild", data);
                }
            }

            {// 修正道友名称
                Map<String, String> data = redis.hgetAll("dao_you");
                for (String k : data.keySet()) {
                    JSONObject object = JSON.parseObject(data.get(k));
//                    object.put("name", "S" + (Integer.parseInt(config.get("id")) - 10000) + "." + object.getString("name"));
                    object.put("name", object.getString("name"));
                    data.put(k, object.toJSONString());
                    logger.info("修正道友名称：{}", object.getString("name"));
                }
                if(data.size() > 0 ) {
                    redis.hmset("dao_you", data);
                }
            }
//        }
    }

    public void merge(Map<String,String> from, Redis formRedis, Map<String,String> to, Redis toRedis) {
        // 合并名称库
        logger.info("合并名称库...");
        Map<String, String> names = formRedis.hgetAll("NAME_MODULE");
        toRedis.hmset("NAME_MODULE", names);
        logger.info("合并完成：" + names.size());

        {// 玩家
            logger.info("迁移玩家...");
            for (String id : names.values()) {
                logger.info("迁移玩家{}", id);
                toRedis.hmset(id, formRedis.hgetAll(id));
            }
            logger.info("合并完成：" + names.size());
        }

        {// 公会
            logger.info("迁移仙盟...");
            for (String key : new String[] { "guild", "guild_auction_log", "guild_bless", "guild_boss_rank", "guild_depot", "guild_impeach", "guild_member", "guild_news" }) {
                try {
                    Map<String, String> data = formRedis.hgetAll(key);
                    if (!data.isEmpty()) {
                        toRedis.hmset(key, data);
                        logger.info("迁移仙盟的" + key);
                    } else {
                        logger.info("仙盟无数据：" + key);
                    }
                } catch (Exception e) {
                    logger.error("迁移仙盟的" + key + "出错了。。。");
                }
            }
        }

        {// 道友
            logger.info("迁移道友...");
            for (String key : new String[] { "dao_you", "dao_you_member" }) {
                Map<String, String> data = formRedis.hgetAll(key);
                if (!data.isEmpty()) {
                    toRedis.hmset(key, data);
                    logger.info("迁移道友的" + key);
                } else {
                    logger.info("道友无数据：" + key);
                }
            }
        }

        {// 合并排行榜.
            logger.info("迁移排行榜...");
            for (String key : new String[] { "LEVEL", "FIGHTPOWER", "FIGHTPOWER_1", "FIGHTPOWER_3", "FIGHTPOWER_5", "GUILD_LEVEL", "Mount", "PET", "XIANYUAN", "HP", "PHY", "MAGIC", "DEMON_TOWER" }) {
                try {
                    Set<Tuple> ts = formRedis.zrangeWithScores("rank/" + from.get("id") + "/" + key);
                    Map<String, Double> rank = new HashMap<>();
                    for (Tuple t : ts) {
                        rank.put(t.getElement(), t.getScore());
                    }
                    toRedis.zadd("rank/" + to.get("id") + "/" + key, rank);
                    logger.info("迁移排行榜的" + key);
                } catch (Exception e) {
                    logger.error("迁移排行榜的" + key + "出错了。。。");
                }
            }
        }

        {// 复活
            logger.info("复活...");
            for (String key : new String[] { "DAILY_RELIVE" }) {
                Map<String, String> data = formRedis.hgetAll(key);
                if (!data.isEmpty()) {
                    toRedis.hmset(key, data);
                    logger.info("迁移复活的" + key);
                } else {
                    logger.info("复活无数据：" + key);
                }
            }
        }

        {// 成长基金,两个服加起来...
            String funds = formRedis.hget(String.valueOf(from.get("id")), "funds");
            if (!StringUtils.isEmpty(funds)) {
                long sum = toRedis.hincrBy(String.valueOf(to.get("id")), "funds", Integer.parseInt(funds));
                logger.info("成长基金和为:" + sum);
            }
        }

        {// 拍卖
            logger.info("拍卖...");
            for (String key : new String[] { "consignment_items" }) {
                Map<String, String> data = formRedis.hgetAll(key);
                if (!data.isEmpty()) {
                    toRedis.hmset(key, data);
                    logger.info("迁移拍卖的" + key);
                } else {
                    logger.info("拍卖无数据：" + key);
                }
            }
        }

        {// 竞拍
            logger.info("竞拍...");
            for (String key : new String[] { "auction_itemsTR" }) {
                Map<String, String> data = formRedis.hgetAll(key);
                if (!data.isEmpty()) {
                    toRedis.hmset(key, data);
                    logger.info("迁移竞拍的" + key);
                } else {
                    logger.info("竞拍无数据：" + key);
                }
            }
        }

        logger.info(from.get("name") + "搞定啦...");
    }

    public GameServer getGameServerInfo(int gameServerId){
        return gameServerMapper.findServerById(gameServerId);
    }

}
