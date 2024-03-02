package com.example.jobala.guest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GuestController {

    @GetMapping("/guest/jobSearch")
    public String jobSearch() {
        return "/guest/jobSearch";
    }

    @GetMapping("/guest/mngForm")
    public String mngForm() {
        return "/guest/resume/myPage/mngForm";
    }

    @GetMapping("/guest/profileForm")
    public String profileForm() {
        return "/guest/resume/myPage/profileForm";
    }

}
