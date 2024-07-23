package com.example.crmlsr.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.crmlsr.entity.Customer;
import com.example.crmlsr.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

/**
 * function：
 * author：lsr
 * date：2024/3/19 14:14
 */
@Service
public class CustomerService extends ServiceImpl<CustomerMapper, Customer> {
}
