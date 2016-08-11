package com.robert.zhihu.entity;

import java.io.Serializable;
import java.util.List;


public class ListPopular implements Serializable {
    public List<Popular> data;

    public ListPopular(List<Popular> data) {
        this.data = data;
    }
}
