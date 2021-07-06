package com.bibao.orderserver.service.impl;

import com.bibao.orderserver.Result;
import com.bibao.orderserver.config.SnowflakeConfig;
import com.bibao.orderserver.model.Orders;
import com.bibao.orderserver.mapper.OrdersMapper;
import com.bibao.orderserver.rpc.client.GoodsClient;
import com.bibao.orderserver.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bibao.orderserver.util.RedisLock;
import com.bibao.orderserver.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.downgoon.snowflake.Snowflake;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-05
 */
@Slf4j
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {
    @Autowired
    Snowflake snowflake;
    @Autowired
    GoodsClient goodsClient;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    RedisLock redisLock;
    private static final ThreadPoolExecutor threadPoolExecutor= new ThreadPoolExecutor(1, 1, 30,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    @Override
    @Transactional
    public Result addOrders(String goodsId, String userId, int number) {
        int redisNumber =0;
        if(redisUtil.get(goodsId)!=null){
            redisNumber = (int)redisUtil.get(goodsId);
        }

        if(!goodsClient.checkNuber(goodsId,number+redisNumber)){
            return Result.error(500,"库存不足");
        }
        log.info(userId);
        final Result[] result = new Result[1];
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
              result[0] =  tryOrders(goodsId, userId, number);
            }
        });
        return result[0];
    }
    @Transactional
    public Result tryOrders(String goodsId, String userId, int number){
        try{
            boolean lock = redisLock.lock(goodsId);
            if (lock) {
                // 执行逻辑操作
                redisUtil.increment(goodsId,number);
                Long ordersId =snowflake.nextId();
                Orders orders = new Orders();
                orders.setOrdersId(String.valueOf(ordersId));
                orders.setGoodsId(goodsId);
                orders.setUserId(userId);
                orders.setNumber(number);

                redisUtil.add(userId,"orders"+ordersId,orders);
                ordersMapper.insert(orders);
                redisLock.delete(goodsId);
                return new Result().setData(ordersId);
            } else {
                // 设置失败次数计数器, 当到达5次时, 返回失败
                int failCount = 1;
                while(failCount <= 5){
                    // 等待100ms重试
                    try {
                        Thread.sleep(100l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (redisLock.lock(goodsId)){
                        // 执行逻辑操作
                        redisLock.delete(goodsId);
                    }else{
                        failCount ++;
                    }
                }
                throw new RuntimeException("现在创建的人太多了, 请稍等再试");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  Result.error(e.toString());
        }finally {
            redisLock.delete(goodsId);
        }

    }

}
