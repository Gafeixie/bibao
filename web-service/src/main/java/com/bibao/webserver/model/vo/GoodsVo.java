package com.bibao.webserver.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author: 谢佳辉
 * @date 2021/5/31 11:50 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GoodsVo extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品类型id
     */
    private String[] goodsTypeId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 价格
     */
    private String price;

    /**
     * 详情描述
     */
    private String text;

    /**
     * 图片id
     */
    private String firstPicUrl;
    /**
     * 商品数量
     */
    private Integer goods_number;

}
