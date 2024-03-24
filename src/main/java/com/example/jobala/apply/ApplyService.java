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

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyJPARepository applyJPARepository;
    private final JobopenJPARepository jobopenJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final ApplyQueryRepository applyQueryRepository;

    public ApplyResponse.ApplyStatusFormResponse getApplyStatusForm(int userId) {
        // 내가 지원한 공고 현황
        List<ApplyResponse.ApplyDTO> appliedPositions = applyQueryRepository.findByUserId(userId);

        // 기업으로부터 받은 공고 제안 현황
        List<ApplyResponse.ApplyDTO2> receivedOffersReviewing = applyQueryRepository.findJopOpenByUserId(userId, "검토중");
        List<ApplyResponse.ApplyDTO2> receivedOffersAccepted = applyQueryRepository.findJopOpenByUserId(userId, "수락");
        List<ApplyResponse.ApplyDTO2> receivedOffersRejected = applyQueryRepository.findJopOpenByUserId(userId, "거절");

        // 응답 객체 생성 및 반환
        return new ApplyResponse.ApplyStatusFormResponse(appliedPositions, receivedOffersReviewing, receivedOffersAccepted, receivedOffersRejected);
    }

    @Transactional
    public void 상태수정(Integer applyId, String status) {
        Apply apply = applyJPARepository.findById(applyId).orElseThrow(() ->
                new RuntimeException("해당 ID로 조회된 지원정보가 없습니다 : " + applyId));

        apply.setState(status);

        applyJPARepository.save(apply);
    }

    @Transactional
    public void 지원후저장(ApplyRequest.ApplyRequestDTO reqDTO, User sessionUser) {
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
