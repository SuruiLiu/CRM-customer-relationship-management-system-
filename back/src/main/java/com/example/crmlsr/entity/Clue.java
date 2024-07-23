package com.example.crmlsr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * function：
 * author：lsr
 * date：2024/3/21 18:28
 */
@Data
@TableName("clue")
public class Clue {

    @TableId(type = IdType.AUTO)
    @TableField(value = "xid")
    private Integer xid;

    @TableField(value = "xname")
    private String xname;

    @TableField(value = "cname")
    private String cname;

    @TableField(value = "xorigin")
    private String xorigin;

    @TableField(value = "xproduct")
    private String xproduct;

    @TableField(value = "xlevel")
    private String xlevel;

    @TableField(value = "xtime")
    private String xtime;

    @TableField(value = "xstate")
    private String xstate;

    @TableField(value = "xman")
    private String xman;

    @TableField(value = "cneed")
    private String cneed;


}
