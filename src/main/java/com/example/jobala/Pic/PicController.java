package com.example.jobala.Pic;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Controller
public class PicController {

    private final String uploadDir = "./image/"; // 파일 업로드 디렉토리

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("imgFile") MultipartFile imgFile) {
        String filename = imgFile.getOriginalFilename(); // 업로드된 파일명 가져오기
        Path filePath = Paths.get(uploadDir + filename); // 파일 경로 생성

        try (OutputStream os = new FileOutputStream(filePath.toFile())) {
            os.write(imgFile.getBytes()); // 파일 쓰기
            return ResponseEntity.ok().body("이미지 업로드가 성공하였습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드 중 오류가 발생하였습니다.");
        }
    }
}