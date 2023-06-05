package com.healthy.gym.service;

import com.healthy.gym.dao.CommunityDao;
import com.healthy.gym.dto.BbsCommentDto;
import com.healthy.gym.dto.BbsCommentParam;
import com.healthy.gym.dto.BbsDto;
import com.healthy.gym.dto.BbsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CommunityService {

    @Autowired
    CommunityDao dao;

    public List<Map<String, Object>> getBestPostList(BbsParam param) {
        return dao.getBestPostList(param);
    }

    public List<Map<String, Object>> getPostList(BbsParam param) {
        return dao.getPostList(param);
    }
    public List<Map<String, Object>> getPost(int bbsseq) {
        return dao.getPost(bbsseq);
    }
    public boolean checkLiking(BbsDto dto) {
        return dao.checkLiking(dto) > 0;
    }

    public List<Map<String, Object>> getPostComment(BbsCommentParam param) {
        return dao.getPostComment(param);
    }
    public boolean writePost(BbsDto dto) {
        return dao.writePost(dto) > 0;
    }
    public boolean writePostComment(BbsCommentDto dto) {
        return dao.writePostComment(dto) > 0;
    }
    public boolean writePostReply(BbsCommentDto dto){
        return dao.writePostReply(dto) > 0;
    }

    public boolean likePost(BbsDto dto) {
        return dao.likePost(dto) > 0;
    }
    public boolean unlikePost(BbsDto dto) {
        return dao.unlikePost(dto) > 0;
    }
    public boolean addLikecount(int bbsseq) {
        return dao.addLikecount(bbsseq) > 0;
    }
    public boolean subtractLikecount(int bbsseq) {
        return dao.subtractLikecount(bbsseq) > 0;
    }

    public boolean deletePost(int bbsseq) {
        return dao.deletePost(bbsseq) > 0;
    }
    public boolean reportPost(int bbsseq) {
        return dao.reportPost(bbsseq) > 0;
    }
    public boolean updatePost(BbsDto dto) {
        return dao.updatePost(dto) > 0;
    }
    public boolean updatePostComment(BbsCommentDto dto) {
        return dao.updatePostComment(dto) > 0;
    }
    public boolean deletePostComment(int commentseq) {
        return dao.deletePostComment(commentseq) > 0;
    }

    public boolean addReadcount(int bbsseq) {
        return dao.addReadcount(bbsseq) > 0;
    }
}
