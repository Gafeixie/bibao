package com.bibao.webserver.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bibao.webserver.Result;
import com.bibao.webserver.mapper.GoodsMapper;
import com.bibao.webserver.mapper.GoodsTypeMapper;
import com.bibao.webserver.model.Goods;
import com.bibao.webserver.model.GoodsType;

import com.bibao.webserver.model.enty.GoodsAndType;
import com.bibao.webserver.model.vo.GoodsVo;
import com.bibao.webserver.service.IGoodsService;
import com.bibao.webserver.service.impl.GoodsServiceImpl;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-05-31
 */
@Slf4j
@CrossOrigin
@RestController
public class GoodsController {
    @Autowired
    IGoodsService iGoodsService;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsTypeMapper goodsTypeMapper;
    @PostMapping("/goods")
    public Result addGoods(@RequestBody GoodsAndType goodsAndType){
        Goods goods = goodsAndType.getGoods();
        GoodsType goodsType = goodsAndType.getGoodsType();
       return iGoodsService.addGoods(goods,goodsType);
    }
    @GetMapping("/goodsList")
    public Result getGoodsList(@RequestParam(value = "type" ,required = false) String type ,
                           @RequestParam(value = "minPrice",required = false)String minPrice,
                           @RequestParam(value = "maxPrice",required = false)String maxPrice){
        if(minPrice!=null&&maxPrice!=null&&Double.valueOf(maxPrice)<Double.valueOf(minPrice)){
            return Result.error("请输入正确的价格区间");
        }
        return iGoodsService.getGoodsList(type, minPrice, maxPrice);

    }
    @GetMapping("/goods")
    public Result getGoods(@RequestParam(value = "goodsId",required = true) String goodsId,
                           @RequestParam(value="userid",required = false)String userId){

        Goods goods = goodsMapper.selectOne(new QueryWrapper<Goods>().eq("goods_id",goodsId));
        GoodsType goodsType = goodsTypeMapper.selectOne(new QueryWrapper<GoodsType>().eq("type_id",goodsId));
        log.info(userId+" "+goodsType.getTypeName1()+" "+goodsType.getTypeName2()+" "+goodsType.getTypeName3()+" "+goodsId);
        return new Result().setData(goods);
    }

    @GetMapping("/goodsNumber")
    public Integer getGoodsNumber(@RequestParam(value = "goodsId",required = true)String goodsId){
        var goods=goodsMapper.selectOne(new QueryWrapper<Goods>().eq("goods_id",goodsId));
        System.out.println(goods);
        return goods.getGoodsNumber();
    }
}
