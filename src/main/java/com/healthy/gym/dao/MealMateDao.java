package com.healthy.gym.dao;

import com.healthy.gym.dto.BbsDto;
import com.healthy.gym.dto.BbsParam;
import com.healthy.gym.dto.FoodDto;
import com.healthy.gym.dto.HealthMateParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MealMateDao {

    List<Map<String, Object>> getMealMatePostList(Map<String, Object> param);
    List<FoodDto> getFoodList(int bbsseq);
    int writePost(BbsDto bbsdto);
    int writeMealMate(List<FoodDto> list);
}
