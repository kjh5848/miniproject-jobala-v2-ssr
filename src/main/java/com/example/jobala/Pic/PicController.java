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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class PicController {

    private final PicRepository picRepository;
    private final String uploadDir = "./image/"; // 파일 업로드 디렉토리

    @PostMapping("/upload")
    public ResponseEntity<String> upload(PicRequest.UploadDTO requestDTO) {

        String title = requestDTO.getTitle();
        MultipartFile imgFile = requestDTO.getImgFile();

        // 2. 파일저장 위치 설정해서 파일을 저장 (UUID 붙여서 롤링)
        String imgFilename = UUID.randomUUID() + "_" + imgFile.getOriginalFilename();
        Path imgPath = Paths.get("./image/" + imgFilename);
        try {
            Files.write(imgPath, imgFile.getBytes());

            // 3. DB에 저장 (title, realFileName)
            picRepository.upload(title, imgFilename);

            // 정상적으로 업로드 및 저장이 완료되었음을 응답
            return ResponseEntity.ok().body("이미지 업로드 및 데이터베이스 저장이 완료되었습니다.");

        } catch (IOException e) {
            e.printStackTrace();
            // 오류가 발생하였음을 응답
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드 중 오류가 발생하였습니다.");
        }
    }
}