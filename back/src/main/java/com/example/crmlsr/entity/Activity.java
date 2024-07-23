package com.example.crmlsr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * function：
 * author：lsr
 * date：2024/3/18 15:53
 */
@Data
@TableName("activity")
public class Activity {

    @TableId(type = IdType.AUTO)
    @TableField(value = "actid")
    private Integer actid;

    @TableField(value = "actname")
    private String actname;

    @TableField(value = "actstate")
    private String actstate;

    @TableField(value = "acttype")
    private String acttype;

    @TableField(value = "actintro")
    private String actintro;

    @TableField(value = "actdescrip")
    private String actdescrip;

    @TableField(value = "start")
    private String start;

    @TableField(value = "end")
    private String end;

    @TableField(value = "actaddress")
    private String actaddress;



}
