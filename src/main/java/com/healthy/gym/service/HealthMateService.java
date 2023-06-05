package com.healthy.gym.service;

import com.healthy.gym.dao.HealthMateDao;
import com.healthy.gym.dto.BbsDto;
import com.healthy.gym.dto.HealthMateDto;
import com.healthy.gym.dto.HealthMateParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HealthMateService {

    @Autowired
    HealthMateDao dao;

    public List<Map<String, Object>> getHealthMateList(HealthMateParam param) {
        return dao.getHealthMateList(param);
    }
    public List<Map<String, Object>> getHealthMatePost(int bbsseq) {
        return dao.getHealthMatePost(bbsseq);
    }

    public boolean writeHealthMatePost(BbsDto bbsdto, HealthMateDto matedto) {
        boolean b = dao.writePost(bbsdto) > 0;
        matedto.setBbsseq(bbsdto.getBbsseq());

        return b & dao.writeHealthMate(matedto) > 0;
    }

    public boolean updateHealthMatePost(BbsDto bbsdto, HealthMateDto matedto) {
        boolean b = dao.updatePost(bbsdto) > 0;
        matedto.setBbsseq(bbsdto.getBbsseq());

        return b & dao.updateHealthMate(matedto) > 0;
    }

    public boolean addReadcount(int bbsseq) {
        return dao.addReadcount(bbsseq) > 0;
    }
}
