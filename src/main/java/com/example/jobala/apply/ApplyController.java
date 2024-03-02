package com.example.jobala.apply;

import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        // 이력서 정보 userId를 통해 가져오기
        Resume resume = resumeRepository.findByUserId(userId);
        // 해당 사용자의 지원자 목록 가져오기
        List<Apply> applies = applyRepository.findByUserId(userId);
        request.setAttribute("resume", resume);
        request.setAttribute("applies", applies);
        return "resumeDetail";
    }

    @GetMapping("/guest/applyStatusForm")
    public String applyStatusForm() {
        return "/guest/resume/myPage/applyStatusForm";
    }

    @GetMapping("/comp/applyPositionForm")
    public String applyPositionForm() {
        return "/comp/jobopen/myPage/applyPositionForm";
    }
}
