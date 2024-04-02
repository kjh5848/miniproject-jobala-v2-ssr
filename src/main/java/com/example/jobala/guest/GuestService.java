package com.example.jobala.guest;

import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._user.User;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class GuestService {

    private final GuestJPARepository guestJPARepository;
    private final GuestQueryRepository guestQueryRepository;
    private final ResumeJPARepository resumeJPARepository;

    // 프로필업데이트
    @Transactional
    public User guestUpdateProfile(GuestRequest.GuestProfileUpdateDTO reqDTO, User sessionUser) {
        User user = guestJPARepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("수정할 프로필이 없습니다."));

        MultipartFile imgFilename = reqDTO.getImgFilename();

        // 사진이 변경되었는지 여부 확인
        boolean isImgChanged = imgFilename != null && !imgFilename.isEmpty();

        // 이미지 파일의 저장 경로 설정
        String webImgPath = null;
        if (isImgChanged) {
            String guestImgFilename = UUID.randomUUID() + "_" + imgFilename.getOriginalFilename();
            Path imgPath = Paths.get("./image/" + guestImgFilename);
            try {
                Files.write(imgPath, imgFilename.getBytes());
                webImgPath = imgPath.toString().replace("\\", "/");
                webImgPath = webImgPath.substring(webImgPath.lastIndexOf("/") + 1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 사진이 변경되었는지 여부에 따라 프로필 업데이트 진행
        if (isImgChanged) {
            // 사진이 변경된 경우: 새로운 이미지 파일 경로와 함께 업데이트
            user.setGuestProfileUpdateDTO(reqDTO, webImgPath);
        } else {
            // 사진이 변경되지 않은 경우: 이전의 이미지 파일 경로를 유지하고 업데이트
            user.setGuestProfileUpdateDTO(reqDTO, user.getImgFilename());
        }

        return user;
    }

    public List<JobopenResponse.ListDTO> jobopenSearch(String skills, GuestResponse.SearchDTO resDTO) {
        return guestQueryRepository.findAll(skills, resDTO);
    }

    public List<JobopenResponse.ListDTO> findAll() {
        return guestQueryRepository.findByJoboopenAll();
    }

    public List<Resume> findResumeByUserId(Integer id) {
        return resumeJPARepository.findByUserId(id);
    }

}