package com.example.jobala.apply;

import com.example.jobala.resume.ResumeRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ApplyController {

    private final HttpSession session;

    private final ApplyRepository applyRepository;
    private final ResumeRepository resumeRepository;

    @GetMapping("/guest/applyStatusForm")
    public String applyStatusForm() {
        return "/guest/resume/myPage/applyStatusForm";
    }

    @GetMapping("/comp/applyPositionForm")
    public String applyPositionForm() {
        return "/comp/jobopen/myPage/applyPositionForm";
    }
}
