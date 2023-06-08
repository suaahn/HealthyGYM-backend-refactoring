package com.healthy.gym.service;

import com.healthy.gym.dao.FollowDao;
import com.healthy.gym.dto.FollowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FollowService {

    @Autowired
    FollowDao dao;

    public List<FollowDto> getFollowingTargets(int memberseq) {
        return dao.getFollowingTargets(memberseq);
    }

    public List<FollowDto> getFollowers(int memberseq) {
        return dao.getFollowers(memberseq);
    }

    public boolean requestFollow(FollowDto dto) {
        return dao.requestFollow(dto) > 0;
    }

    public boolean requestUnfollow(FollowDto dto) {
        return dao.requestUnfollow(dto) > 0;
    }

    public boolean checkFollow(FollowDto dto) {
        return dao.checkFollow(dto) > 0;
    }
}
