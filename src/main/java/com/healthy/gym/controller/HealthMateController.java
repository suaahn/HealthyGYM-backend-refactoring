package com.healthy.gym.controller;

import com.healthy.gym.dto.BbsDto;
import com.healthy.gym.dto.HealthMateDto;
import com.healthy.gym.dto.HealthMateParam;
import com.healthy.gym.service.HealthMateService;
import com.healthy.gym.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mate/health")
public class HealthMateController {

    @Autowired
    HealthMateService service;

    @GetMapping
    public List<Map<String, Object>> getHealthMateList(HealthMateParam param, boolean[] bodypart) {

        String[] body = {"back", "chest", "shoulder", "arm", "abs", "leg", "run"};

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < bodypart.length; i++) {
            if(bodypart[i]) list.add(body[i]);
        }
        param.setBodypart(list.toArray(new String[0]));

        HealthMateParam params = (HealthMateParam) Utility.setPageParam(param);

        return service.getHealthMateList(params);
    }

    @GetMapping("/{bbsseq}")
    public List<Map<String, Object>> getHealthMatePost(@PathVariable int bbsseq, boolean visit) {

        // 조회수
        if(visit) service.addReadcount(bbsseq);

        return service.getHealthMatePost(bbsseq);
    }

    @PostMapping("/write")
    public boolean writeHealthMatePost(BbsDto bbsdto, HealthMateDto dto, boolean[] bodypart) {

        HealthMateDto matedto = new HealthMateDto(0, dto.getAddressfirst(), dto.getAddresssecond(),
                                                    dto.getCenter(), dto.getMdate(), dto.getMtime(),
                                                    bodypart[0], bodypart[1], bodypart[2], bodypart[3],
                                                    bodypart[4], bodypart[5], bodypart[6]);

        return service.writeHealthMatePost(bbsdto, matedto);
    }

    @PostMapping("/update")
    public boolean updateHealthMatePost(BbsDto bbsdto, HealthMateDto dto, boolean[] bodypart) {

        HealthMateDto matedto = new HealthMateDto(0, dto.getAddressfirst(), dto.getAddresssecond(),
                                                    dto.getCenter(), dto.getMdate(), dto.getMtime(),
                                                    bodypart[0], bodypart[1], bodypart[2], bodypart[3],
                                                    bodypart[4], bodypart[5], bodypart[6]);

        return service.updateHealthMatePost(bbsdto, matedto);
    }
}
