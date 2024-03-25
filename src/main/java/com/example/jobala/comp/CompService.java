package com.example.jobala.comp;

import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._user.User;
import com.example.jobala.guest.GuestJPARepository;
import com.example.jobala.guest.GuestRequest;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;
import com.example.jobala.resume.ResumeQueryRepository;
import com.example.jobala.resume.ResumeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompService {
    private final CompJPARepository compJPARepository;
    private final ResumeQueryRepository resumeQueryRepository;

    @Transactional
    public User 프로필업데이트(CompRequest.CompProfileUpdateDTO reqDTO, User sessionUser) {
        User user = compJPARepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("수정할 프로필이 없습니다."));

        MultipartFile imgFilename = reqDTO.getImgFilename();

        // 이미지 파일의 저장 경로 설정
        String GuestImgFilename = UUID.randomUUID() + "_" + imgFilename.getOriginalFilename();
        Path imgPath = Paths.get("./image/" + GuestImgFilename);

        try {
            Files.write(imgPath, imgFilename.getBytes());
            String webImgPath = imgPath.toString().replace("\\", "/");
            webImgPath = webImgPath.substring(webImgPath.lastIndexOf("/") + 1);

            user.setCompProfileUpdateDTO(reqDTO,webImgPath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public List<ResumeResponse.ListDTO> 이력서검색하기(String resumeTitle) {
        List<ResumeResponse.ListDTO> resumeList = resumeQueryRepository.findByResumeTitleLike(resumeTitle);
        return resumeList;
    }
}
