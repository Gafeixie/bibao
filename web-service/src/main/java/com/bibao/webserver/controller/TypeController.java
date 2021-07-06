package com.bibao.webserver.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bibao.webserver.Result;
import com.bibao.webserver.mapper.TypeMapper;
import com.bibao.webserver.model.Goods;
import com.bibao.webserver.model.Type;
import com.bibao.webserver.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-20
 */
@RestController
public class TypeController {
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    IGoodsService goodsService;


    @GetMapping("/type")
    public Result getLike(@RequestParam(value = "userid",required = false) String userId){
        try{
            if(userId.isEmpty()){
                return goodsService.getGoodsList(null,null,null);
            }else {
                Type type = typeMapper.selectOne(new QueryWrapper<Type>().eq("userId",userId));
               List<Goods> list1 = (List<Goods>) goodsService.getGoodsList(type.getTypeOne(),null,null).get("data");
                List<Goods> list2 = (List<Goods>) goodsService.getGoodsList(type.getTypeTwo(),null,null).get("data");
                List<Goods> list3 = (List<Goods>) goodsService.getGoodsList(type.getTypeThree(),null,null).get("data");
                Map<String,Object> map =new ConcurrentHashMap<>();
                add(map,list1);
                add(map,list2);
                add(map,list3);
                List list4 = new ArrayList();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    list4.add(entry.getValue());
                }
                return new Result().setData(list4);
            }
        }catch (Exception e){
            return  Result.error();
        }
    }
    public void add(Map<String,Object>map , List<Goods> list){

                for (Goods goods : list) {
                    map.put(goods.getGoodsId(),goods);
                }
            }

    }

