package com.healthy.gym.service;

import com.healthy.gym.dao.MealMateDao;
import com.healthy.gym.dto.BbsDto;
import com.healthy.gym.dto.BbsParam;
import com.healthy.gym.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MealMateService {

    @Autowired
    MealMateDao dao;

    public List<Map<String, Object>> getMealMatePostList(Map<String, Object> param) {
        return dao.getMealMatePostList(param);
    }

    public List<FoodDto> getFoodList(int bbsseq) {
        return dao.getFoodList(bbsseq);
    }

    public boolean writeMealMatePost(BbsDto bbsdto, List<FoodDto> list) {
        boolean b = dao.writePost(bbsdto) > 0;
        int bbsseq = bbsdto.getBbsseq();

        for (FoodDto dto : list) {
            dto.setBbsseq(bbsseq);
        }
        return b & dao.writeMealMate(list) > 0;
    }
}
