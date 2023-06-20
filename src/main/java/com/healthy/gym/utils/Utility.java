package com.healthy.gym.utils;

import com.healthy.gym.dto.PageParam;

public class Utility {

    public static PageParam setPageParam(PageParam param, int limitPerPage) {
        int page = param.getPage();
        param.setStart(1 + (page * limitPerPage));
        param.setEnd((page + 1) * limitPerPage);
        return param;
    }
}
