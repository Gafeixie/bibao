package com.bibao.orderserver.rpc.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author: 谢佳辉
 * @date 2021/6/6 11:23 下午
 */
@Component
public class GoodsClient {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;

    public String echoAppName(){
        //Access through the combination of LoadBalanceClient and RestTemplate
        ServiceInstance serviceInstance = loadBalancerClient.choose("shopping-server");
        String path = String.format("http://%s:%s/%s?goodsId=716333067559641088",serviceInstance.getHost(),serviceInstance.getPort(),"goods");
        System.out.println("request path:" +path);
        return restTemplate.getForObject(path,String.class);
    }
    public Boolean checkNuber(String userId,int number){
        ServiceInstance serviceInstance = loadBalancerClient.choose("shopping-server");
        String path = String.format("http://%s:%s/%s?goodsId=%s",serviceInstance.getHost(),serviceInstance.getPort(),"goodsNumber",userId);
        int numbers = restTemplate.getForObject(path,Integer.class);
        if(numbers>=number){
            return true;
        }else {
            return false;
        }
    }
}
