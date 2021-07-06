package com.bibao.orderserver.mapper;

import com.bibao.orderserver.model.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-05
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}
