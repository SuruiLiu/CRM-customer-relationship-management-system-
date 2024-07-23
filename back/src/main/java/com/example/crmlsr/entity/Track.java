package com.example.crmlsr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * function：
 * author：lsr
 * date：2024/3/26 16:58
 */
@Data
@TableName("track")
public class Track {

    @TableId(type = IdType.AUTO)
    @TableField(value = "tid")
    private Integer tid;

    @TableField(value = "tname")
    private String tname;

    @TableField(value = "name")
    private String name;

    @TableField(value = "tway")
    private String tway;

    @TableField(value = "tinfo")
    private String tinfo;

    @TableField(value = "tman")
    private String tman;

    @TableField(value = "linkman")
    private String linkman;

}
