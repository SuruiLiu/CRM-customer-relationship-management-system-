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
@TableName("linkman")
public class Linkman {


    @TableId(type = IdType.AUTO)
    @TableField(value = "lid")
    private Integer lid;

    @TableField(value = "lname")
    private String lname;

    @TableField(value = "lman")
    private String lman;

    @TableField(value = "lapart")
    private String lapart;

    @TableField(value = "lgender")
    private String lgender;

    @TableField(value = "lcname")
    private String lcname;

    @TableField(value = "lphone")
    private String lphone;

    @TableField(value = "ldescr")
    private String ldescr;



}
