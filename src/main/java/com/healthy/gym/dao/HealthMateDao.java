package com.healthy.gym.dao;

import com.healthy.gym.dto.BbsDto;
import com.healthy.gym.dto.HealthMateDto;
import com.healthy.gym.dto.HealthMateParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface HealthMateDao {

    List<Map<String, Object>> getHealthMateList(HealthMateParam param);
    List<Map<String, Object>> getHealthMatePost(int bbsseq);

    int writePost(BbsDto dto);
    int writeHealthMate(HealthMateDto dto);

    int updatePost(BbsDto dto);
    int updateHealthMate(HealthMateDto dto);

    int addReadcount(int bbsseq);
}
