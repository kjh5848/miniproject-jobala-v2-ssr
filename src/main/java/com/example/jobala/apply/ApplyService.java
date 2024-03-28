package com.example.jobala.apply;

import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.resume.ResumeJPARepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyJPARepository applyJPARepository;
    private final JobopenJPARepository jobopenJPARepository;
    private final ResumeJPARepository resumeJPARepository;


    // 상태수정
    @Transactional
    public void statusUpdate(Integer applyId, String status) {
        Apply apply = applyJPARepository.findById(applyId).orElseThrow(() ->
                new Exception404("해당 ID로 조회된 지원정보가 없습니다 : " + applyId));
    
        apply.setState(status);
    }

    // 지원후저장
    @Transactional
    public void saveAfterApply(ApplyRequest.ApplyRequestDTO reqDTO, User sessionUser) {
        Jobopen jobopen = jobopenJPARepository.findById(reqDTO.getJobopenId())
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다"));
        Resume resume = resumeJPARepository.findById(reqDTO.getResumeId())
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));

        applyJPARepository.save(reqDTO.toEntity(resume,jobopen,sessionUser));
    }

    public List<ApplyResponse.CompPositionDTO> findApplyCompByUserId(Integer id) {
        List<Apply> applyList = applyJPARepository.findByUserId(id);
        return applyList.stream().map(apply -> new ApplyResponse.CompPositionDTO(apply)).toList();
    }
}
