package com.healthy.gym.dao;

import com.healthy.gym.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberDao {

    MemberDto findBySeq(int memberseq);
    MemberDto findByEmail(String email);
    int findSeqByEmail(String email);
    String findProviderByEmail(String email);
    int findAuthByEmail(String email);
    int existsByEmail(String email);
    int existsByNickname(String nickname);

    int signup(MemberDto dto);
    int signupOAuth(MemberDto dto);
    int registerMemberinfo(String email);

    int resetPwd(MemberDto dto);
    int updateMailKey(MemberDto dto);
}
