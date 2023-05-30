package com.healthy.gym.controller;

import com.healthy.gym.dto.BbsDto;
import com.healthy.gym.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    HomeService service;

    @GetMapping("posts/best")
    public List<BbsDto> getHomeBestPosts() {

        return service.getHomeBestPosts();
    }

    @GetMapping("posts/{bbstag}")
    public List<BbsDto> getHomePosts(@PathVariable int bbstag) {

        return service.getHomePosts(bbstag);
    }
}
