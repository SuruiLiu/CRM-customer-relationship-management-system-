package com.example.crmlsr.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crmlsr.entity.Product;
import com.example.crmlsr.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
 * function：
 * author：lsr
 * date：2024/3/16 14:23
 */
@Service
public class ProductService extends ServiceImpl<ProductMapper,Product> {}
