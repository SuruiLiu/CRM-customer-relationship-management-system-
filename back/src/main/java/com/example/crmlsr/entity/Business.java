package com.example.crmlsr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * function：
 * author：lsr
 * date：2024/3/21 18:24
 */
@Data
@TableName("business")
public class Business {

    @TableId(type = IdType.AUTO)
    @TableField(value = "bid")
    private Integer bid;

    @TableField(value = "bname")
    private String bname;

    @TableField(value = "bstate")
    private String bstate;

    @TableField(value = "bsum")
    private String bsum;

    @TableField(value = "bpdate")
    private String bpdate;

    @TableField(value = "bphase")
    private String bphase;

    @TableField(value = "lname")
    private String lname;

    @TableField(value = "bman")
    private String bman;

    @TableField(value = "clevel")
    private Integer clevel;

    @TableField(value = "bmanlevel")
    private Integer bmanlevel;

    @TableField(value = "bmannum")
    private Integer bmannum;

    @TableField(value = "suit")
    private Float suit;

    @TableField(value = "satisfaction")
    private Float satisfaction;

    @TableField(value = "tech")
    private Integer tech;

    @TableField(value = "profit")
    private Integer profit;

    @TableField(value = "state")
    private Integer state;

    @TableField(value = "input")
    private Integer input;

    @TableField(value = "maxsum")
    private Integer maxsum;

    @TableField(value = "win")
    private Integer win;

    @TableField(value = "rate")
    private Float rate;
}
