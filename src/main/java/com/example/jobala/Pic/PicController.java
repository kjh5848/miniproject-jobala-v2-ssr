package com.example.jobala.Pic;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/upload")
    public ResponseEntity<String> upload(PicRequest.UploadDTO requestDTO) {
        String title = requestDTO.getTitle();
        MultipartFile imgFile = requestDTO.getImgFile();

        String imgFilename = UUID.randomUUID() + "_" + imgFile.getOriginalFilename();
        Path imgPath = Paths.get("./image/" + imgFilename);

        try {
            Files.write(imgPath, imgFile.getBytes());

            // 파일 경로를 디비에 저장
            picRepository.upload(title, imgPath.toString());

            return ResponseEntity.ok().body("이미지 업로드 및 데이터베이스 저장이 완료되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("이미지 업로드 중 오류가 발생하였습니다.");
        }
    }
}