package com.bibao.orderserver.service;

import com.bibao.orderserver.Result;
import com.bibao.orderserver.model.Orders;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-05
 */

public interface IOrdersService extends IService<Orders> {
    public Result addOrders(String goodsId, String userId, int number);
}
