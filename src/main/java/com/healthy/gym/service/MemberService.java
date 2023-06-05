package com.healthy.gym.service;

import com.healthy.gym.dao.MemberDao;
import com.healthy.gym.dto.MemberDto;
import com.healthy.gym.utils.MailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    @Autowired
    MemberDao dao;

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @Autowired
    JavaMailSender mailSender;

    public MemberDto findBySeq(int memberseq) {
        return dao.findBySeq(memberseq);
    }

    public MemberDto findByEmail(String email) {
        MemberDto dto = dao.findByEmail(email);
        if(dto == null) {
            return null;
        }
        return dto;
    }

    public int findSeqByEmail(String email) {
        return dao.findSeqByEmail(email);
    }

    public String findProviderByEmail(String email) {
        return dao.findProviderByEmail(email);
    }

    public int findAuthByEmail(String email) {
        return dao.findAuthByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return dao.existsByEmail(email) > 0;
    }

    public boolean existsByNickname(String nickname) {
        return dao.existsByNickname(nickname) > 0;
    }

    public boolean registerMemberinfo(String email) {
        return dao.registerMemberinfo(email) > 0;
    }

    public boolean resetPwd(MemberDto dto) {
        //dto.setPwd(passwordEncoder.encode(dto.getPwd()));
        return dao.resetPwd(dto) > 0;
    }

    public int updateMailKey(MemberDto dto) {
        return dao.updateMailKey(dto);
    }

    // 이메일 인증번호 발송
    public void emailAuthCode(String email, String mailKey) throws Exception {

        String mailContent = "<div style='text-align:center;'>" +
                "<h1 style='color:black;'>건강해GYM 메일인증</h1>" +
                "<p style='font-size:18px; color:#333; margin-top:30px; margin-bottom:20px;'>환영합니다!</p>" +
                "<p style='font-size:16px; color:#555; margin-top:20px; margin-bottom:30px;'>아래 인증번호를 확인해주세요.</p>" +
                "<div style='background-color:#E9ECEF; color:#333; font-size:20px; padding:10px; display:inline-block; border-radius:5px; margin-bottom:30px;'>" + mailKey + "</div>" +
                "<p style='font-size:14px; color:#999; margin-top:30px;'>본 이메일은 발신 전용입니다. 문의 사항은 고객센터를 이용해주세요.</p>" +
                "<p style='font-size:14px; color:#999;'>건강해GYM</p>" +
                "</div>";

        MailHandler mailHandler = new MailHandler(mailSender);
        mailHandler.setSubject("[건강해GYM] 회원가입 인증메일 입니다.");
        mailHandler.setText(mailContent);
        mailHandler.setFrom("healthyzym@gmail.com", "건강해GYM");
        mailHandler.setTo(email);
        mailHandler.send();
    }

    // 비밀번호 재설정 이메일 발송
    public void emailPwdReset(MemberDto dto) throws Exception {

        // 메일 내용
        String mailContent = "<div style='text-align:center;'>" +
                "<h1 style='color:black;'>건강해GYM 비밀번호 재설정</h1>" +
                "<p style='font-size:18px; color:#333; margin-top:30px; margin-bottom:20px;'>안녕하세요, " + dto.getNickname() + "님!</p>" +
                "<p style='font-size:16px; color:#555; margin-top:20px; margin-bottom:30px;'>비밀번호를 재설정 하시려면 아래 버튼을 클릭해주세요.</p>" +
                "<a href='http://localhost:9100/reset-password?email=" + dto.getEmail() + "&mail_key=" + dto.getMailkey() + "' style='display:inline-block; background-color:#FF4136; color:#fff; font-size:16px; text-align:center; padding:12px 20px; border-radius:5px; text-decoration:none; margin-bottom:30px;'>비밀번호 재설정</a>" +
                "<p style='font-size:14px; color:#999; margin-top:30px;'>본 이메일은 발신 전용입니다. 문의 사항은 고객센터를 이용해주세요.</p>" +
                "<p style='font-size:14px; color:#999;'>건강해GYM</p>" +
                "</div>";

        // 인증을 위한 이메일 발송
        MailHandler mailHandler = new MailHandler(mailSender);
        mailHandler.setSubject("[건강해GYM] " + dto.getNickname() + "님 비밀번호 재설정 이메일입니다."); // 메일제목
        mailHandler.setText(mailContent);
        mailHandler.setFrom("healthyzym@gmail.com", "건강해GYM");
        mailHandler.setTo(dto.getEmail());
        mailHandler.send();
    }
}
