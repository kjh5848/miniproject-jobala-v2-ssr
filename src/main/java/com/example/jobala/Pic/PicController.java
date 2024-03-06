package com.example.jobala.Pic;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
    public void upload(@RequestParam("imgFile") MultipartFile imgFile, HttpServletResponse response) {
        String filename = imgFile.getOriginalFilename(); // 업로드된 파일명 가져오기
        Path file = Paths.get(uploadDir + filename); // 파일 경로 생성

        try (OutputStream os = new FileOutputStream(file.toFile())) {
            os.write(imgFile.getBytes()); // 파일 쓰기
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}