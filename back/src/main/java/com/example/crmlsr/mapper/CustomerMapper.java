package com.example.crmlsr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.crmlsr.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
}
