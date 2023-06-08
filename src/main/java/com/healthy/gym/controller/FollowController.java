package com.healthy.gym.controller;

import com.healthy.gym.dto.FollowDto;
import com.healthy.gym.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    FollowService service;

    @PostMapping(value = "/request")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public boolean requestFollow(@RequestBody FollowDto followDto) {

        service.requestFollow(followDto);

        return true;
    }

    @PostMapping(value = "/request/unfollow")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public boolean requestUnfollow(@RequestBody FollowDto followDto) {

        service.requestUnfollow(followDto);

        return true;
    }

    @PostMapping(value = "/check/follow")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public boolean checkFollow(@RequestBody FollowDto followDto) {

        if(service.checkFollow(followDto)){
            return true;
        } else {
            return false;
        }
    }

    // 맞팔(f4f) 확인
    @PostMapping(value = "/check/fff")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public boolean checkFFF(@RequestBody FollowDto dto) {

        int loginMemberseq = dto.getMemberseq();
        int userMemberseq = dto.getFoltarget();

        if(loginMemberseq == userMemberseq) {
            return true;
        } else if(dto.getMemberseq() == 0){
            return false;
        } else {

            FollowDto followDto1 = new FollowDto(loginMemberseq, userMemberseq);
            FollowDto followDto2 = new FollowDto(userMemberseq, loginMemberseq);

            boolean result1 = service.checkFollow(followDto1);
            boolean result2 = service.checkFollow(followDto2);

            return result1 && result2;
        }
    }

    // 이건 뭐지?
    /*@PostMapping(value = "/confirm/follow/me")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public boolean confirmFollowMe(@RequestBody MemberDto memberDto) {

        int memberseq = memberDto.getMemberseq();
        String nickname = service.findMemberById(memberseq).getNickname();

        if(memberDto.getNickname().equals(nickname)){
            return true;
        } else {
            return false;
        }
    }*/
}
