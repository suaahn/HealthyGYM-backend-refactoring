package com.healthy.gym.controller;

import com.healthy.gym.dto.MemberDto;
import com.healthy.gym.service.MemberService;
import com.healthy.gym.utils.TempKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    MemberService service;

    @GetMapping("/{memberseq}")
    public MemberDto getMember(@PathVariable int memberseq) {
        return service.findBySeq(memberseq);
    }

    @GetMapping("/check-email") // 이메일 중복체크
    public String checkEmail(String email) {
        if(!service.existsByEmail(email)) {
            return "OK";
        }
        // 이메일이 이미 존재할 경우, 가입경로 반환
        return service.findProviderByEmail(email);
    }

    @GetMapping("/check-nickname")  // 닉네임 중복체크
    public boolean checkNickname(String nickname) {
        return service.existsByNickname(nickname);
    }


    @PostMapping("/reset-password")
    public boolean resetPwd(MemberDto dto) {
        String mailkey = service.findByEmail(dto.getEmail()).getMailkey();

        if(!mailkey.equals(dto.getMailkey())) {
            return false;
        }

        return service.resetPwd(dto);
    }

    @PostMapping("/email-auth")
    public String emailAuth(String email) throws Exception {

        // 인증키 생성
        String mailKey = new TempKey().getKey(6, true); // 6자리 소문자
        System.out.println(mailKey);

        // 인증코드 이메일 발송
        service.emailAuthCode(email, mailKey);

        // 인증키 프론트에 보내주기
        return mailKey;
    }

    @PostMapping("/email-password")
    public String emailPwd(String email) throws Exception {

        MemberDto dto = service.findByEmail(email);

        if(dto == null) {
            return "NO";
        } else if(dto.getProvider().equals("kakao")) {
            return "kakao";
        } else if(dto.getProvider().equals("google")) {
            return "google";
        }

        // 인증키 생성
        String mailKey = new TempKey().getKey(10, false); // 10자리 대소문자
        System.out.println(mailKey);

        dto.setMailkey(mailKey);

        // 비밀번호 재설정 이메일 발송
        service.emailPwdReset(dto);
        // 인증키 저장
        service.updateMailKey(dto);

        // 인증키 프론트에 보내주기
        return mailKey;
    }
}
