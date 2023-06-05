package com.healthy.gym.service;

import com.healthy.gym.dao.SearchDao;
import com.healthy.gym.dto.BbsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SearchService {

    @Autowired
    SearchDao dao;

    public List<Map<String, Object>> findPostByKeyword(BbsParam param) {
        return dao.findPostByKeyword(param);
    }
}
