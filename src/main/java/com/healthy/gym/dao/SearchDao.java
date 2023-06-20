package com.healthy.gym.dao;

import com.healthy.gym.dto.BbsParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SearchDao {

    List<Map<String, Object>> findPostByKeyword(BbsParam param);
    List<Map<String, Object>> findPostOrderByAccuracy(BbsParam param);
}
