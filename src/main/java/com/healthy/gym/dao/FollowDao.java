package com.healthy.gym.dao;

import com.healthy.gym.dto.FollowDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FollowDao {

    List<FollowDto> getFollowingTargets(int memberseq);
    List<FollowDto> getFollowers(int memberseq);
    int requestFollow(FollowDto dto);
    int requestUnfollow(FollowDto dto);
    int checkFollow(FollowDto dto);
}
