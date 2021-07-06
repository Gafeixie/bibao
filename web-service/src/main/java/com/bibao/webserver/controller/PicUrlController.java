package com.bibao.webserver.controller;


import com.bibao.webserver.Result;
import com.bibao.webserver.mapper.PicUrlMapper;
import com.bibao.webserver.model.PicUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-03
 */
@CrossOrigin
@RestController
public class PicUrlController {
    @Autowired
    PicUrlMapper picUrlMapper;
    @PostMapping("/pic")
    public Result insertPic(@RequestBody PicUrl picUrl){
      return new Result().setData(picUrlMapper.insert(picUrl))  ;

    }
    @PutMapping("pic")
    public Result updatePic(@RequestBody PicUrl picUrl){
        return new Result().setData(picUrlMapper.updateById(picUrl));
    }
}
