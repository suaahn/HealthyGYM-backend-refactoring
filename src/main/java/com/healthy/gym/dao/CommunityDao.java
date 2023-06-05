package com.healthy.gym.dao;

import com.healthy.gym.dto.BbsCommentDto;
import com.healthy.gym.dto.BbsCommentParam;
import com.healthy.gym.dto.BbsDto;
import com.healthy.gym.dto.BbsParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CommunityDao {

    List<Map<String, Object>> getBestPostList(BbsParam param);
    List<Map<String, Object>> getPostList(BbsParam param);
    List<Map<String, Object>> getPost(int bbsseq);
    int checkLiking(BbsDto dto);

    List<Map<String, Object>> getPostComment(BbsCommentParam param);
    int writePost(BbsDto dto);
    int writePostComment(BbsCommentDto dto);
    int writePostReply(BbsCommentDto dto);

    int likePost(BbsDto dto);
    int unlikePost(BbsDto dto);
    int addLikecount(int bbsseq);
    int subtractLikecount(int bbsseq);

    int deletePost(int bbsseq);
    int reportPost(int bbsseq);
    int updatePost(BbsDto dto);
    int updatePostComment(BbsCommentDto dto);
    int deletePostComment(int commentseq);

    int addReadcount(int bbsseq);
}
