package com.jyjx.yxdl.controller.admin;


import com.jyjx.yxdl.common.Redis;
import com.jyjx.yxdl.entity.GameServer;
import com.jyjx.yxdl.service.GameServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;


import java.util.*;

@RestController
@RequestMapping(path = "admin/server")
public class ServerController extends AdminBaseController{

    @Autowired
    public GameServerService gameServerService;

    @RequestMapping(path = "getGameServer")
    public Map<String,Object> getGameServer(@RequestParam(value = "appId") int appId){

        List<GameServer> gServerList = gameServerService.getGameServer(appId);

        return displaySuccess(gServerList);

    }

    @RequestMapping(path = "getGameServerInfo")
    public Object getGameServerInfo(@RequestParam(value = "gameServerId") int gameServerId){
        return displaySuccess(gameServerService.getGameServerInfo(gameServerId));
    }


    @RequestMapping(path = "merge")
    public Object merge(@RequestParam(value = "fromId") String fromId,@RequestParam(value = "toId") String toId,@RequestParam(value = "fromIndex") String fromIndex,@RequestParam(value = "toIndex") String toIndex,@RequestParam(value = "fromName") String fromName,@RequestParam(value = "toName") String toName,@RequestParam(value = "commonIndex") int commonIndex){
//        String fromId = "130";  //from 合区后消失的区服
//        String toId = "127";  //to 最终合并到的区服
        Map<String,String> from = new HashMap<>();
        from.put("host","r-bp1qdckw6ntz7bvcu0.redis.rds.aliyuncs.com"); //from 合区后消失的区服
        from.put("port","6379");
        from.put("index",fromIndex);
        from.put("id",fromId);
        from.put("name",fromName);
        Map<String,String> to = new HashMap<>();
        to.put("host","r-bp1qdckw6ntz7bvcu0.redis.rds.aliyuncs.com"); //to 最终合并到的区服
        to.put("port","6379");
        to.put("index",toIndex);
        to.put("id",toId);
        to.put("name",toName);

        gameServerService.merge(from,to);

        Redis common = new Redis("r-bp1qdckw6ntz7bvcu0.redis.rds.aliyuncs.com",6379,commonIndex); // 整个专服的全局缓存redis库
        Map<String,String> com123 = common.hgetAll(fromId);
        Map<String,String> com124 = common.hgetAll(toId);
        for(Map.Entry<String,String> entry : com124.entrySet()){
            for(Map.Entry<String,String> ent : com123.entrySet()){
                if(entry.getKey().equals(ent.getKey())){
                    entry.setValue(entry.getValue().substring(0,entry.getValue().length()-1)+","+ent.getValue().substring(1,ent.getValue().length()));
                }
            }
        }
        //com123 是两个区所有的数据
        com123.putAll(com124);
        // common 整个专服的全局缓存redis库
        common.hmset(toId,com123);
        System.out.println(fromId);
        System.out.println(toId);
        return "OK";
    }

    public static void main(String[] args) {
        Redis common = new Redis("127.0.0.1",6379,10);
        common.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {       //收到消息会调用
                System.out.println(String.format("receive redis published message, channel %s, message %s", channel, message));
            }
            @Override
            public void onSubscribe(String channel, int subscribedChannels) {    //订阅了频道会调用
                System.out.println(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
                        channel, subscribedChannels));
            }
            @Override
            public void onUnsubscribe(String channel, int subscribedChannels) {   //取消订阅 会调用
                System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d",
                        channel, subscribedChannels));

            }
        },"dingyue");
    }


}
