package com.bibao.webserver.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bibao.webserver.Result;
import com.bibao.webserver.mapper.GoodsTypeMapper;
import com.bibao.webserver.model.GoodsType;
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
@CrossOrigin
@RestController
public class GoodsTypeController {
    @Autowired
    GoodsTypeMapper goodsTypeMapper;
    @PutMapping("goodsType")
    public Result changeType(GoodsType goodsType){
        return new Result().setData(goodsTypeMapper.updateById(goodsType));
    }
    @GetMapping("goodsType")
    public Result getType(@RequestParam("typeid") String type){
        return new Result().setData(goodsTypeMapper.selectList(new QueryWrapper<GoodsType>().eq("type_name1",type).or()
                .eq("type_name2",type).or().eq("type_name3",type)));
    }
}
