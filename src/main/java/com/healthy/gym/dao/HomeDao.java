package com.healthy.gym.dao;

import com.healthy.gym.dto.BbsDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HomeDao {

    List<BbsDto> getHomeBestPosts();
    List<BbsDto> getHomePosts(int bbstag);
}
