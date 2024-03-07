package com.example.jobala.Pic;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/upload")
    public ResponseEntity<String> upload(PicRequest.UploadDTO reqDTO) {
        String title = reqDTO.getTitle();
        MultipartFile imgFile = reqDTO.getImgFile();

        // 이미지 파일의 저장 경로 설정
        String imgFilename = UUID.randomUUID() + "_" + imgFile.getOriginalFilename();
        Path imgPath = Paths.get("./image/" + imgFilename);

        try {
            // 이미지 파일을 지정된 경로에 저장
            Files.write(imgPath, imgFile.getBytes());

            // 이미지 파일의 저장 경로를 웹에서 인식할 수 있는 주소로 변경
            String webImgPath = imgPath.toString().replace("\\", "/");
            webImgPath = webImgPath.substring(webImgPath.lastIndexOf("/") + 1);

            // 파일 경로를 디비에 저장
            picRepository.upload(title, webImgPath);

            return ResponseEntity.ok().body("이미지 업로드 및 데이터베이스 저장이 완료되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("이미지 업로드 중 오류가 발생하였습니다.");
        }
    }
}