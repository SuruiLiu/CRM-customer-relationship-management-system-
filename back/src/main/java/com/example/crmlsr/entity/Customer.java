package com.example.crmlsr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * function：
 * author：lsr
 * date：2024/3/19 14:06
 */
@Data
@TableName("customer")
public class Customer {

    @TableId(type = IdType.AUTO)
    @TableField(value = "cid")
    private Integer cid;

    @TableField(value = "cname")
    private String cname;

    @TableField(value = "ctype")
    private String ctype;

    @TableField(value = "clevel")
    private String clevel;

    @TableField(value = "cbusiness")
    private String cbusiness;

    @TableField(value = "corigin")
    private String corigin;

    @TableField(value = "cman")
    private String cman;

    @TableField(value = "caddress")
    private String caddress;


}
