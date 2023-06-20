package com.healthy.gym.dao;

import com.healthy.gym.dto.BbsDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface HomeDao {

    List<Map<String, Object>> getHomeBestPosts();
    List<BbsDto> getHomePosts(int bbstag);
}
