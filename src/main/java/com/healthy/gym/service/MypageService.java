package com.healthy.gym.service;

import com.healthy.gym.dao.MemberDao;
import com.healthy.gym.dao.MypageDao;
import com.healthy.gym.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class MypageService {
    @Autowired
    MypageDao dao;

    /*Window Os*/
    public static String localPath =  "C:/upload/";

    /*Mac Os*/
    //public static String localPath = "/Users/admin/springboot_img/";

    public MemberinfoDto findMemberinfoBySeq(int memberseq) {
        return dao.findMemberinfoBySeq(memberseq);
    }

    public boolean updateProfile(ProfileDto profileDto) throws IOException {
        // 프로필 이미지도 수정했을 때
        if (profileDto.getImage() != null) {
            MultipartFile imageFile = profileDto.getImage();
            String originalFileName = imageFile.getOriginalFilename();    //오리지날 파일명
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

            String storedFileName = UUID.randomUUID() + extension;
            String savePath = localPath + "profile/" + storedFileName;
            imageFile.transferTo(new File(savePath));

            profileDto.setProfile(storedFileName);
        }
        return dao.updateProfile(profileDto) > 0;
    }

    public boolean updatePwd(MemberDto dto) {
        //dto.setPwd(passwordEncoder.encode(dto.getPwd()));

        return dao.updatePwd(dto) > 0;
    }

    public boolean withdrawMember(MemberDto dto) {
        return dao.withdrawMember(dto) > 0;
    }

    public List<Map<String, Object>> getMyPostList(BbsDto dto) {
        return dao.getMyPostList(dto);
    }

    public List<Map<String, Object>> getMyAllPostList(BbsDto dto) {
        return dao.getMyAllPostList(dto);
    }

    public List<Map<String, Object>> getMyCmtList(BbsDto dto) {
        return dao.getMyCmtList(dto);
    }
    public List<Map<String, Object>> getMyAllCmtList(BbsDto dto) {
        return dao.getMyAllCmtList(dto);
    }

    public List<Map<String, Object>> getMyLikedPostList(BbsDto dto) {
        return dao.getMyLikedPostList(dto);
    }

    public List<Map<String, Object>> getMyAllLikedPostList(BbsDto dto) {
        return dao.getMyAllLikedPostList(dto);
    }
}
