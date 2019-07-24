package com.aaa.service.impl;

import com.aaa.dao.Empdao;
import com.aaa.service.EmpBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmpBizImpl implements EmpBiz {

    @Autowired
    Empdao empdao;

    @Override
    public List<Map<String, Object>> findAllEmp() {
        return empdao.findAllEmp();
    }
}