package com.healthy.gym.controller;

import com.healthy.gym.dto.InbodyDto;
import com.healthy.gym.dto.MemberDto;
import com.healthy.gym.dto.ProfileDto;
import com.healthy.gym.service.InbodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/inbody")
public class InbodyController {

    @Autowired
    InbodyService service;

    /*Window Os*/
    public static String localPath =  "C:/upload/";

    /*Mac Os*/
    //public static String localPath = "/Users/admin/springboot_img/";

    @GetMapping("/list")
    public Map<String, Object> getInbodyList(@RequestBody MemberDto dto) {

        List<InbodyDto> list = service.getInbodyList(dto.getMemberseq());

        Map<String, Object> map = new HashMap<>();
        map.put("list", list);

        return map;
    }

    @PostMapping(value = "/ocr/upload")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public InbodyDto ocr_fileUpload(@ModelAttribute ProfileDto profileDto) {

        InbodyDto result = new InbodyDto();

        MultipartFile uploadFile = profileDto.getUploadFile();

        String originalFileName = uploadFile.getOriginalFilename();    //오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));    //파일 확장자

        // UUID 사용하여 파일명 중복 문제 처리
        String filename = UUID.randomUUID() + extension;
        String filepath = localPath + "inbody/" + filename;

        System.out.println("업로드 경로 : " + filepath);

        try {
            // 업로드된 파일을 임시 파일로 저장
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            os.write(uploadFile.getBytes());
            os.close();

            // 이미지 크기를 변경하여 저장
            BufferedImage originalImage = ImageIO.read(new File(filepath));
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            double aspectRatio = (double) width / height;
            int newWidth = 1000;
            int newHeight = (int) (newWidth / aspectRatio);
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g.dispose();
            ImageIO.write(resizedImage, "png", new File(filepath)); // 이미지 파일 확장자명에 상관없이 png 파일로 저장

        } catch (Exception e) {
            e.printStackTrace();
            result.setImgpath("이미지 저장 실패");
            return result;
        }

        result = service.ocr(profileDto.getMemberseq(), filepath, filename);
        System.out.println("result:" + result);
        return result;
    }

    @PostMapping(value = "/save")
    public String saveInbody(@RequestBody InbodyDto dto){

        service.saveInbody(dto.getInbodyseq());

        return "ok";
    }

    @PostMapping(value = "/delete")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String deleteInbody(@RequestBody InbodyDto dto) {

        service.deleteInbody(dto.getInbodyseq());

        return "ok";
    }

}
