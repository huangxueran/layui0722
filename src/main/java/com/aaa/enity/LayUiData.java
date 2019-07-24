package com.aaa.enity;

import java.util.List;

/**
 * @Author: 陈建
 * @Date: 2019/7/21 0021 18:07
 * @Version 1.0
 */
public class LayUiData {
    private int code;
    private String msg;
    private  Long count;
    private List<?> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
