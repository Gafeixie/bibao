package com.bibao.webserver.mapper;

import com.bibao.webserver.model.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2021-05-31
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

}
