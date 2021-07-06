package com.bibao.orderserver.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2021-06-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Orders extends Model {

    private static final long serialVersionUID = 1L;


    /**
     * 订单主键
     */
    private String ordersId;

    /**
     * 物品主键
     */
    private String goodsId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 状态
     */
    private Boolean staues;

    /**
     * 实付金额
     */
    private String actuallyPaid;

    private Integer number;
}
