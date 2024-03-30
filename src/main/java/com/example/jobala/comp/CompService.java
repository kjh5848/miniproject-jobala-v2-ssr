package com.example.jobala.comp;

import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._user.User;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala.apply.ApplyJPARepository;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.Resume;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompService {
    private final CompJPARepository compJPARepository;
    private final UserJPARepository userJPARepository;
    private final CompQueryRepository compQueryRepository;
    private final ApplyJPARepository applyJPARepository;


    public List<ResumeResponse.ListDTO> searchResumes(String skills, CompResponse.SearchDTO resDTO) {
        return compQueryRepository.findAll(skills, resDTO);
    }

    // 이력서 목록 가져오기
    public List<ResumeResponse.ListDTO> listAllResumes() {
        List<ResumeResponse.ListDTO> resumes = compQueryRepository.findResumeAll();
        if (resumes.isEmpty()) {
            throw new Exception404("이력서가 없습니다.");
        }
        return resumes;
    }

    // 이력서 상세정보 가져오기
    public List<Resume> getResumeDetail(Integer id) {
        return compQueryRepository.findResumeById(id);
    }

    // 채용공고리스트 가져오기
    public List<JobopenResponse.DTO> searchjobopenList(Integer userId) {
        List<Jobopen> temp = compQueryRepository.findJobopenById(userId);
        List<JobopenResponse.DTO> jobopenList = temp.stream().map(jobopen -> new JobopenResponse.DTO(jobopen)).collect(Collectors.toList());

        jobopenList.forEach(dto -> {
            int count = applyJPARepository.countJobopenApplyById(dto.getId());
            dto.setCount(count);
        });

        return jobopenList;
    }

    // 기업회원프로필
    @Transactional(readOnly = true)
    public User getCompanyProfile(Integer userId) {
        return userJPARepository.findById(userId)
                .orElseThrow(() -> new Exception404("유저를 찾을 수 없습니다."));
    }

    // 프로필업데이트
    @Transactional
    public User compUpdateProfile(CompRequest.CompProfileUpdateDTO reqDTO, User sessionUser) {
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

            user.setCompProfileUpdateDTO(reqDTO, webImgPath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
