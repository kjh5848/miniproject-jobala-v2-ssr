package com.example.jobala.guest;

import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._core.utill.Paging;
import com.example.jobala._user.User;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;
import com.example.jobala.resume.Resume;
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
    private final Paging paging;

    // 프로필업데이트
    @Transactional
    public User guestUpdateProfile(GuestRequest.GuestProfileUpdateDTO reqDTO, User sessionUser) {
        User user = guestJPARepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("수정할 프로필이 없습니다.")).getUser();

        MultipartFile imgFilename = reqDTO.getImgFilename();

        // 이미지 파일의 저장 경로 설정
        String GuestImgFilename = UUID.randomUUID() + "_" + imgFilename.getOriginalFilename();
        Path imgPath = Paths.get("./image/" + GuestImgFilename);

        try {
            Files.write(imgPath, imgFilename.getBytes());
            String webImgPath = imgPath.toString().replace("\\", "/");
            webImgPath = webImgPath.substring(webImgPath.lastIndexOf("/") + 1);

            user.setGuestProfileUpdateDTO(reqDTO,webImgPath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    //공고 검색 결과 페이징
    public Page<JobopenResponse.ListDTO> jobOpenSearch(int page,String skills, GuestResponse.SearchDTO resDTO) {
        Pageable pagigng =  paging.jobOpenSearchPaging(page);

        return (Page<JobopenResponse.ListDTO>) guestQueryRepository.findAll(skills, resDTO, pagigng);
    }

    public List<JobopenResponse.ListDTO> findAll() {
        return guestQueryRepository.findByJoboopenAll();
    }

    public List<Resume> findResumeByUserId(Integer id) {
        return resumeJPARepository.findByUserId(id);
    }

    //이력서 페이징 하기 위한 목록 조회
    public Page<Resume> resumesFindAll(int page, int size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"id"));

        return guestJPARepository.findAll(pageable);
    }
}