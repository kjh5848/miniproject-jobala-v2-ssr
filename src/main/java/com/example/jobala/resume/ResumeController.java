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

    @GetMapping("/resume/writeForm")
    public String detailform() {
        return "/guest/resume/detailForm";
    }
}
