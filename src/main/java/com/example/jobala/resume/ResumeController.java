package com.example.jobala.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ResumeController {

    @Autowired
    private ResumeRepository resumeRepository;

    @GetMapping("/guest/resume/writeForm")
    public String writeForm() {
        return "/guest/resume/writeForm";
    }

    @GetMapping("/guest/resume/UpdateForm")
    public String updateForm() {
        return "/guest/resume/updateForm";
    }

    @GetMapping("/guest/resume/{id}")
    public String detailForm(@PathVariable int id) {


        return "/guest/resume/detailForm/";
    }

    @PostMapping("/guest/resume/write")
    public String write(ResumeRequest.SaveDTO resumeSaveDTO) {
        int userId = 1;
        resumeRepository.save(resumeSaveDTO, userId);
        return "redirect:/guest/mngForm";
    }


}
