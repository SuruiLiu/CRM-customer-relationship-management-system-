package com.example.crmlsr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * function：
 * author：lsr
 * date：2024/3/14 16:13
 */
@Data
@TableName("product")
public class Product {

    @TableId(type = IdType.AUTO)
    @TableField(value = "p_id")
    private Integer pId;

    @TableField(value = "p_name")
    private String pName;

    @TableField(value = "p_type")
    private String pType;

    @TableField(value = "p_price")
    private String pPrice;

    @TableField(value = "p_intro")
    private String pIntro;

    @TableField(value = "p_picture")
    private String pPicture;

}
