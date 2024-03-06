package com.example.jobala.Pic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String upload(PicRequest.UploadDTO requestDTO, HttpServletRequest request) throws IOException {
        // 1. 데이터 전달 받고
        String title = requestDTO.getTitle();
        if (title == null) {
            // 타이틀이 null일 경우 처리 로직 추가
            // 예를 들어 기본 타이틀을 설정하거나 오류 메시지를 반환할 수 있습니다.
        } else {
            // 타이틀이 null이 아닌 경우 정상적으로 처리
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
        }
        return "/guest/resume/save"; // 적절한 리턴 값으로 변경
    }
}