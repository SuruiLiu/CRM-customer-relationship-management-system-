package com.example.crmlsr.controller;

import com.example.crmlsr.entity.PredictData;
import com.example.crmlsr.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * function：
 * author：lsr
 * date：2024/4/2 18:57
 */

@RestController
@RequestMapping("/home")
public class PredictionController {

    private static final String FLASK_API_URL1 = "http://localhost:5000/predict";  // Python Flask服务的API地址
    private static final String FLASK_API_URL2 = "http://localhost:5001/predictrate";  

    // 使用 RestTemplate 发送 POST 请求到 Python Flask服务
    @PostMapping("/predict")
    public Result predict(@RequestBody PredictData data) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(FLASK_API_URL1, data, String.class);
        System.out.println(result);
        return Result.success(result);
    }

    @PostMapping("/predictrate")
    public Result predictrate(@RequestBody PredictData data) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(FLASK_API_URL2, data, String.class);
        System.out.println(result);
        return Result.success(result);
    }
}
