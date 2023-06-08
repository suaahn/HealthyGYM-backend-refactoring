package com.healthy.gym.controller;

import com.healthy.gym.dto.BbsDto;
import com.healthy.gym.dto.MemberDto;
import com.healthy.gym.dto.MemberinfoDto;
import com.healthy.gym.dto.ProfileDto;
import com.healthy.gym.service.MemberService;
import com.healthy.gym.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    MypageService service;

    @Autowired
    MemberService memberService;

    /*Window Os*/
    public static String localPath =  "C:/upload/";

    /*Mac Os*/
    //public static String localPath = "/Users/admin/springboot_img/";

    @GetMapping(value = "/images/{folderName}/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<FileSystemResource> getImage(@PathVariable String folderName, @PathVariable String imageName) {

        String imagePath = localPath + folderName + "/" + imageName;

        File imageFile = new File(imagePath);

        return ResponseEntity.ok()
                .contentLength(imageFile.length())
                .body(new FileSystemResource(imageFile));
    }

    @GetMapping("/memberinfo")
    public MemberinfoDto findMemberinfo(@RequestBody MemberDto dto) {

        int memberseq = dto.getMemberseq();

        return service.findMemberinfoBySeq(memberseq);
    }
    @PostMapping(value = "/profile")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String updateProfile(@ModelAttribute ProfileDto profileDto) throws IOException {

        service.updateProfile(profileDto);

        return memberService.findBySeq(profileDto.getMemberseq()).getProfileimg();
    }

    // Multipart file이 null일 경우 프로필이미지를 제외한 나머지 회원정보 업데이트
    @PostMapping(value = "/profile/null")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String updateProfileNull(@RequestBody ProfileDto profileDto) throws IOException {

        service.updateProfile(profileDto);

        return memberService.findBySeq(profileDto.getMemberseq()).getProfileimg();
    }

    @PostMapping(value = "/password")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String updatePwd(@RequestBody MemberDto dto) {

        service.updatePwd(dto);
        return "ok";
    }

    @PostMapping(value = "/withdraw")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String withdrawMember(@RequestBody MemberDto dto) {

        String ranEmail = UUID.randomUUID() + "@healthygym.com";
        dto.setEmail(ranEmail);
        dto.setAuth(3);

        service.withdrawMember(dto);

        return "ok";
    }

    @GetMapping(value = "/posts")
    public List<Map<String, Object>> getMyPostList(@RequestBody BbsDto dto) {

        if(dto.getBbstag() != 0){
            return service.getMyPostList(dto);
        } else {
            return service.getMyAllPostList(dto);
        }
    }

    @GetMapping(value = "/comments")
    public List<Map<String, Object>> getMyCmtList(@RequestBody BbsDto dto) {

        if(dto.getBbstag() != 0){
            return service.getMyCmtList(dto);
        } else {
            return service.getMyAllCmtList(dto);
        }
    }

    @GetMapping(value = "/liked-posts")
    public List<Map<String, Object>> getMyLikedPostList(@RequestBody BbsDto dto) {

        if(dto.getBbstag() != 0){
            return service.getMyLikedPostList(dto);
        } else {
            return service.getMyAllLikedPostList(dto);
        }
    }

}
