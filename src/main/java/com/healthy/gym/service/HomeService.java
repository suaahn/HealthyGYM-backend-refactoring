package com.healthy.gym.service;

import com.healthy.gym.dao.HomeDao;
import com.healthy.gym.dto.BbsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HomeService {

    @Autowired
    HomeDao dao;

    public List<Map<String, Object>> getHomeBestPosts() {
        return dao.getHomeBestPosts();
    }

    public List<BbsDto> getHomePosts(int bbstag) {
        return dao.getHomePosts(bbstag);
    }
}
