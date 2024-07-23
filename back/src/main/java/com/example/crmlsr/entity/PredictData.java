package com.example.crmlsr.entity;

import lombok.Data;

/**
 * function：
 * author：lsr
 * date：2024/4/3 15:34
 */
@Data
public class PredictData {
    // 定义与 Python Flask API 接口数据格式一致的数据类
    private int clevel;
    private int bmanlevel;
    private int bmannum;
    private double suit;
    private double satisfaction;
    private int tech;
    private int profit;
    private int state;
    private int input;
    private int maxsum;
    private double rate;

    // 省略 getter 和 setter
}
