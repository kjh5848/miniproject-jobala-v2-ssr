package com.example.jobala._user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GuestController {


    @GetMapping("/guest/joinForm")
    public String loginForm(){
        return "/guest/joinForm";
    }

    @GetMapping("/resume/updateForm")
    public String resumeUpdateForm(){
        return "/guest/resume/updateForm";
    }

    @GetMapping("/resume/detailForm")
    public String resumeDetailForm(){
        return "/guest/resume/detailForm";
    }
}
