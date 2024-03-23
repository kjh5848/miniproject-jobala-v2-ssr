package com.example.jobala.apply;

import com.example.jobala._core.errors.exception.Exception404;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyJPARepository applyJPARepository;
    private final JobopenJPARepository jobopenJPARepository;
    private final ResumeJPARepository resumeJPARepository;

    @Transactional
    public void 이력서로지원후저장(ApplyRequest.ResumeApplyDTO reqDTO, User sessionUser) {
        Jobopen jobopen = jobopenJPARepository.findById(reqDTO.getJobopenId())
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다"));
        Resume resume = resumeJPARepository.findById(reqDTO.getResumeId())
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));

        Apply apply = Apply.builder()
                .user(sessionUser)
                .jobopen(jobopen)
                .resume(resume)
                .state("검토중")
                .build();

        applyJPARepository.save(apply);
    }

    @Transactional
    public void 공고로지원후저장(ApplyRequest.JobopenApplyDTO reqDTO, User sessionUser) {
        Jobopen jobopen = jobopenJPARepository.findById(reqDTO.getJobopenId())
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다"));
        Resume resume = resumeJPARepository.findById(reqDTO.getResumeId())
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));

        Apply apply = Apply.builder()
                .user(sessionUser)
                .jobopen(jobopen)
                .resume(resume)
                .state("검토중")
                .build();

        applyJPARepository.save(apply);
    }

}
