package com.healthy.gym.controller;

import com.healthy.gym.dto.BbsCommentDto;
import com.healthy.gym.dto.BbsCommentParam;
import com.healthy.gym.dto.BbsDto;
import com.healthy.gym.dto.BbsParam;
import com.healthy.gym.service.CommunityService;
import com.healthy.gym.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/communities")
public class CommunityController {

    @Autowired
    CommunityService service;

    @GetMapping("/best")
    public List<Map<String, Object>> getBestPostList(BbsParam param) {

        BbsParam params = (BbsParam) Utility.setPageParam(param);

        return service.getBestPostList(params);
    }

    @GetMapping("/{bbstag}")
    public List<Map<String, Object>> getPostList(BbsParam param) {

        BbsParam params = (BbsParam) Utility.setPageParam(param);

        return service.getPostList(params);
    }

    @GetMapping("/{bbstag}/posts/{bbsseq}")
    public List<Map<String, Object>> getPost(BbsDto dto, boolean visit) {

        // 게시글 상세 정보
        List<Map<String, Object>> detail = service.getPost(dto.getBbsseq());

        // 조회수
        if(visit) service.addReadcount(dto.getBbsseq());

        // 로그인한 유저의 좋아요 여부
        boolean liking = false;
        if(dto.getMemberseq() != 0) {
            liking = service.checkLiking(dto);
        }
        detail.get(0).put("liking", liking);

        return detail;
    }

    @GetMapping("/{bbstag}/posts/{bbsseq}/comments")
    public List<Map<String, Object>> getPostComment(BbsCommentParam param) {

        BbsCommentParam params = (BbsCommentParam)Utility.setPageParam(param);

        return service.getPostComment(params);
    }

    @PostMapping("/write")
    public String writePost(BbsDto dto) {
        System.out.println(dto.toString());

        if(service.writePost(dto)) {
            return "OK";
        } else {
            return "NO";
        }
    }

    @PostMapping("/write-comment")
    public String writePostComment(BbsCommentDto dto) {

        if(service.writePostComment(dto)) {
            return "OK";
        } else {
            return "NO";
        }
    }

    @PostMapping("/write-reply")
    public String writebbsreply(BbsCommentDto dto) {

        if(service.writePostReply(dto)) {
            return "OK";
        } else {
            return "NO";
        }
    }

    @PostMapping("/like")
    public String likePost(BbsDto dto) {

        if(service.likePost(dto) & service.addLikecount(dto.getBbsseq())) {
            return "OK";
        } else {
            return "NO";
        }
    }

    @PostMapping("/unlike")
    public String unlikePost(BbsDto dto) {

        if(service.unlikePost(dto) & service.subtractLikecount(dto.getBbsseq())) {
            return "OK";
        } else {
            return "NO";
        }
    }

    @PostMapping("/delete")
    public String deletePost(int bbsseq) {

        if(service.deletePost(bbsseq)) {
            return "OK";
        } else {
            return "NO";
        }
    }

    @PostMapping("/report")
    public String reportPost(int bbsseq) {

        if(service.reportPost(bbsseq)) {
            return "OK";
        } else {
            return "NO";
        }
    }

    @PostMapping("/update")
    public String updatePost(BbsDto dto) {

        if(service.updatePost(dto)) {
            return "OK";
        } else {
            return "NO";
        }
    }

    @PostMapping("/update-comment")
    public String updatePostComment(BbsCommentDto dto) {

        if(service.updatePostComment(dto)) {
            return "OK";
        } else {
            return "NO";
        }
    }
    @PostMapping("/delete-comment")
    public String deletePostComment(int commentseq) {

        if(service.deletePostComment(commentseq)) {
            return "OK";
        } else {
            return "NO";
        }
    }
}
