package com.example.crmlsr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.crmlsr.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
