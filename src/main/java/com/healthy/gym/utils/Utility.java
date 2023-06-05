package com.healthy.gym.utils;

import com.healthy.gym.dto.PageParam;

public class Utility {

    public static PageParam setPageParam(PageParam param) {
        int page = param.getPage();
        param.setStart(1 + (page * 10));
        param.setEnd((page + 1) * 10);
        return param;
    }
}
