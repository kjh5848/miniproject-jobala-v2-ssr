package com.example.jobala.apply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ApplyController {

    @GetMapping("/guest/applyStatusForm")
    public String applyStatusForm() {
        return "/guest/resume/myPage/applyStatusForm";
    }

    @GetMapping("/comp/applyPositionForm")
    public String applyPositionForm() {
        return "/comp/jobopen/myPage/applyPositionForm";
    }
}
