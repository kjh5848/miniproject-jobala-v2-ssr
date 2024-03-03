package com.example.jobala.apply;

import com.example.jobala._core.util.ApiUtil;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    @GetMapping("/resume/{userId}")
    public String showResumeDetail(@PathVariable Integer userId, HttpServletRequest request) {
        System.out.println("사용자 ID: " + userId);

        // 이력서 정보 userId를 통해 가져오기
        Resume resume = resumeRepository.findByUserId(userId);
        if (resume == null) {
            // 예외 처리 또는 오류 메시지 반환
            return "resumeNotFound"; // 예시로 반환하는 페이지 이름
        }
        System.out.println("로드된 이력서 정보: " + resume);

        // 해당 사용자가 지원한 모든 공고의 ID 가져오기
        List<Integer> jobOpenIds = applyRepository.findJobOpenIdsByUserId(userId);

        // 해당 공고에 지원한 모든 지원자 목록 가져오기
        List<Apply> applies = new ArrayList<>();
        for (Integer jobOpenId : jobOpenIds) {
            List<Apply> appliesForJobOpen = applyRepository.findByJobOpenId(jobOpenId);
            applies.addAll(appliesForJobOpen);
        }

        request.setAttribute("resume", resume);
        request.setAttribute("applies", applies);
        return "resumeDetail";
    }

    @GetMapping("/guest/applyStatusForm")
    public String applyStatusForm() {
        return "/guest/_myPage/applyStatusForm";
    }

    @GetMapping("/comp/applyPositionForm")
    public String applyPositionForm() {
        return "/comp/_myPage/applyPositionForm";
    }

    @RestController
    @RequestMapping("/api/applicants")
    public class ApplyApiController {

        private final ApplyRepository applyRepository;

        public ApplyApiController(ApplyRepository applyRepository) {
            this.applyRepository = applyRepository;
        }

        @GetMapping("/{jobOpenId}")
        public ResponseEntity<ApiUtil<List<Apply>>> getApplicantsByJobOpenId(@PathVariable Integer jobOpenId) {
            List<Apply> applicants = applyRepository.findByJobOpenId(jobOpenId);
            return ResponseEntity.ok(new ApiUtil<>(applicants));
        }
    }
}
