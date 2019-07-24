package com.aaa.controller;

import com.aaa.enity.LayUiData;
import com.aaa.service.EmpBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class EmpController {
    /**
     * servive 层
     */
    @Autowired
    EmpBiz empBizImpl;

    @RequestMapping("/toList")
     public String toList(){
        return  "list";
    }

    @RequestMapping("/findAllEmp")
    @ResponseBody
    public  LayUiData findAllEmp(){
        LayUiData layUiData=new LayUiData();
        List<Map<String, Object>> mapList = empBizImpl.findAllEmp();
        layUiData.setData(mapList);
        layUiData.setCode(0);
        return  layUiData;
    }
}