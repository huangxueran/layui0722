package com.aaa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//给他一个bean
@Component
@Mapper
public interface Empdao {
    /**
     * 查询所有没有参数  查询所有的emp  员工      springboot   大写  下化线  字符
     */
      List<Map<String,Object>> findAllEmp();
}