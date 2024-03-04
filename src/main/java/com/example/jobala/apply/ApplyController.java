package com.example.jobala.apply;

import com.example.jobala._core.util.ApiUtil;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApplyController {

    private final HttpSession session;

    private final ApplyRepository applyRepository;
    private final ResumeRepository resumeRepository;

    // 이력서 상세페이지에서 지원자 목록을 가져와서 카드 생성
    @GetMapping("/applicants/{jobopenId}")
    public ResponseEntity<ApiUtil<List<ApplyWithResume>>> getApplicantsWithResumesByJobOpenId(@PathVariable Integer jobopenId) {
        List<Apply> applies = applyRepository.findByJobOpenId(jobopenId);
        List<ApplyWithResume> appliesWithResumes = new ArrayList<>();

        for (Apply apply : applies) {
            Resume resume = resumeRepository.findByUserId(apply.getUserId());
            appliesWithResumes.add(new ApplyWithResume(apply, resume));
        }

        return ResponseEntity.ok(new ApiUtil<>(appliesWithResumes));
    }

    @Data
    @AllArgsConstructor
    private static class ApplyWithResume {
        private Apply apply;
        private Resume resume;
    }

    @GetMapping("/applyStatusForm")
    public String applyStatusForm() {
        return "/guest/_myPage/applyStatusForm";
    }

    @GetMapping("/applyPositionForm")
    public String applyPositionForm() {
        return "/comp/_myPage/applyPositionForm";

    }
}
