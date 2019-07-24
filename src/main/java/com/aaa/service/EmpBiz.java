package com.aaa.service;

import java.util.List;
import java.util.Map;


/**
 * empbizimpl的接口
 */
public interface EmpBiz {
    /**
     *    查询全表
     * @return
     */
    List<Map<String,Object>> findAllEmp();
}