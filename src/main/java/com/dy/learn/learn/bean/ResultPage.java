package com.dy.learn.learn.bean;

import java.util.List;

public class ResultPage<T> extends Result{

    private List<T> data;

    @Override
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
