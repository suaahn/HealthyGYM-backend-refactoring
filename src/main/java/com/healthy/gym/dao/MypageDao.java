package com.healthy.gym.dao;

import com.healthy.gym.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MypageDao {

    MemberinfoDto findMemberinfoBySeq(int memberseq);
    int updateProfile(ProfileDto dto);
    int updatePwd(MemberDto dto);
    int withdrawMember(MemberDto dto);

    List<Map<String, Object>> getMyPostList(BbsDto dto);
    List<Map<String, Object>> getMyAllPostList(BbsDto dto);
    List<Map<String, Object>> getMyCmtList(BbsDto dto);
    List<Map<String, Object>> getMyAllCmtList(BbsDto dto);
    List<Map<String, Object>> getMyLikedPostList(BbsDto dto);
    List<Map<String, Object>> getMyAllLikedPostList(BbsDto dto);

}
