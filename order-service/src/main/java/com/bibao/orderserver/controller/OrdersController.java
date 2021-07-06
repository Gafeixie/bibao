package com.bibao.orderserver.controller;



import com.bibao.orderserver.Result;
import com.bibao.orderserver.service.IOrdersService;
import com.bibao.orderserver.service.impl.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-05
 */
@RestController

public class OrdersController {
    @Autowired
    IOrdersService iOrdersService;

    @PostMapping("/orders")
    public Result addOrdrs(@RequestParam(value = "goodsId",required = true)String goodsId,
                           @RequestParam(value = "userId",required = true)String userId,
                           @RequestParam(value = "number",required = true)int number){
          return   iOrdersService.addOrders(goodsId,userId,number);

    }
    @DeleteMapping("/orders")
    public Result delOrders(@RequestParam(value = "userId")String userId,
                            @RequestParam(value = "ordersId")String ordersId){
        return null;
    }
}
