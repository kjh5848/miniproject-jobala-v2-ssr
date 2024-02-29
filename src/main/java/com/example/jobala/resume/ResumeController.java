package com.example.jobala.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ResumeController {

    @GetMapping("/resume/writeForm")
    public String writeForm() {
        return "/guest/resume/writeForm";
    }

    @GetMapping("/resume/UpdateForm")
    public String updateForm() {
        return "/guest/resume/updateForm";
    }

    @GetMapping("/resume/detailForm")
    public String detailForm() {
        return "/guest/resume/detailForm";
    }
}
