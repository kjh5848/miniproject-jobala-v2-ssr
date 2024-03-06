package com.example.jobala.Pic;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class PicController {

    private final PicRepository picRepository;

    @PostMapping("/PicUpload")
    public String PicUpload(PicRequest.UploadDTO requestDTO, @RequestParam int id, HttpServletRequest request) throws IOException {
        // 1. 데이터 전달 받고
        String title = requestDTO.getTitle();
        MultipartFile imgFile = requestDTO.getImgFile();
        System.out.println(title);
        System.out.println(imgFile);

        // 2. 파일저장 위치 설정해서 파일을 저장 (UUID 붙여서 롤링)
        String imgFilename = UUID.randomUUID() + "_" + imgFile.getOriginalFilename();
        Path imgPath = Paths.get("./image/" + imgFilename);
        try {
            Files.write(imgPath, imgFile.getBytes());

            // 3. DB에 저장 (title, realFileName)
            picRepository.PicUpload(title, imgFilename);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Pic pic = picRepository.findById(id);
        request.setAttribute("pic", pic); // HttpServletRequest를 사용하여 pic 객체를 추가

        return "/guest/resume/save";
    }
}