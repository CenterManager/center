package com.jyjx.yxdl.controller.admin;

import com.jyjx.yxdl.common.Redis;
import com.jyjx.yxdl.entity.FeeOrder;
import com.jyjx.yxdl.entity.GameServer;
import com.jyjx.yxdl.entity.PayProduct;
import com.jyjx.yxdl.mapper.PayProductMapper;
import com.jyjx.yxdl.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.*;


@RestController
@RequestMapping(path = "admin/pay")
public class PayController extends  AdminBaseController{

    @Autowired
    public PayService payService;
    @Autowired
    public OrderInfoService orderInfoService;
    @Autowired
    public GameServerService gameServerService;
    @Autowired
    public PayProductMapper payProductMapper;
    @Autowired
    public SimulatePayService simulatePayService;
    @Autowired
    public RedisService redisService;



    private String roleKey = "NAME_MODULE";


    @RequestMapping(path = "getPayList")
    public Map<String,Object> getPayList(@RequestParam(value = "page") int page,@RequestParam(value = "pageSize") int pageSize){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("stateCode",1);
        resultMap.put("data",payService.getPayList(page,pageSize));
        return resultMap;
    }

    @RequestMapping(path = "getPayCount")
    public Map<String,Object> getPayCount(){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("stateCode",1);
        resultMap.put("data",payService.getPayCount());
        return resultMap;
    }

    @RequestMapping(path = "getPayGroup")
    public Map<String,Object> getPayGroup(@RequestParam(value = "page") int page,@RequestParam(value = "pageSize") int pageSize,@RequestParam(value = "appId",required = false) Integer appId,@RequestParam(value = "femaleBag",required = false) Integer femaleBag,@RequestParam(value = "intervalTimer",required = false) String intervalTimer){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("data",payService.getPayGroup(page,pageSize,appId,femaleBag,intervalTimer));
        dataMap.put("count",payService.getGroupCount(appId,femaleBag,intervalTimer));
        resultMap.put("stateCode",1);
        resultMap.put("data",dataMap);
        return resultMap;
    }

    @RequestMapping(path = "getAllPayProduct")
    public Map<String,Object> getAllPayProduct(){
        return displaySuccess(payProductMapper.getAllPayProduct());
    }

    @RequestMapping(path = "payPost")
    public Map<String,Object> payPost(@RequestParam(value = "appId") int appId,@RequestParam(value = "gameServer") int serverId,@RequestParam(value = "role") String role,
    @RequestParam(value = "money") int money,@RequestParam(value = "redisHostId") int redisHostId){
        String redisHost = redisService.getRedisInfo(redisHostId).getRedisHost();
        String setex = null;
        PayProduct payProduct = payProductMapper.findByMoney(money);
        GameServer gServer = gameServerService.findServerById(serverId);
        Redis redis = redis = new Redis(redisHost,6379,gServer.getDbIndex());
        Jedis jedis = redis.getJedis();
        String roleId = jedis.hget(roleKey,role);
        String orderId = orderInfoService.createOrder(String.valueOf(appId),"1000",String.valueOf(serverId),String.valueOf(appId),roleId,String.valueOf(money));
        String orderKey = "feeOrder/" + orderId;
        if (!jedis.exists(orderKey)){
            int productId = payProduct.getPayProductId();
            FeeOrder po = new FeeOrder();
            po.setOrderId(orderId);
            po.setCreatetime(new Date());
            po.setPlayerId(roleId);
            po.setProductId(productId);
            // 小于 100 算月卡
            if (productId < 100) {
                po.setCard(true);
            }
            // 大于300算超值礼包
            else if (productId > 300) {
                po.setSuperPackage(true);
            }

            // 5天过期
            setex = jedis.setex(orderKey,60 * 60 * 24 * 1, payService.serialize(po));

        }
        if(!setex.equals("OK")){
            return displaySuccess("创建订单失败");
        }
        String result = orderInfoService.notifyOrder(orderId);
        if(!result.equals("SUCCESS")){
            return displaySuccess("发货失败");
        }
        simulatePayService.addSimulatePay(role,new Date(),gServer.getServerName(),money/100,orderId);
        return displaySuccess("发货成功");
    }

    @RequestMapping(path = "getSimulateList")
    public Map<String,Object> getSimulateList(@RequestParam(value = "page") int page,@RequestParam(value = "pageSize") int pageSize){
        Map<String,Object> dataMap = new HashMap<>();

        dataMap.put("list",simulatePayService.getSimulateList(page,pageSize));
        dataMap.put("count",simulatePayService.getSimulateCount());
        return displaySuccess(dataMap);
    }

    @RequestMapping(path = "getAllPayMoney")
    public Map<String,Object> getAllPayMoney(){
        return displaySuccess( payService.getAllPayMoney() );
    }



}
