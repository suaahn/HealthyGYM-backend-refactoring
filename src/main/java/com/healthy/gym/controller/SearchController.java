package com.healthy.gym.controller;

import com.healthy.gym.dto.BbsParam;
import com.healthy.gym.service.SearchService;
import com.healthy.gym.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SearchController {

    @Autowired
    SearchService service;

    @GetMapping("/search")
    public List<Map<String, Object>> findPostByKeyword(BbsParam param){

        BbsParam params = (BbsParam) Utility.setPageParam(param);

        if(params.getSearch() == null || params.getSearch().equals("")) {
            params.setSearch("");
        }

        return service.findPostByKeyword(params);
    }
}
