package com.example.jobala.comp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CompController {

    @GetMapping("/comp/scoutList")
    public String scoutList() {
        return "/comp/scoutList";
    }

    @GetMapping("/comp/mngForm")
    public String mngForm() {
        return "/comp/jobopen/myPage/mngForm";
    }

    @GetMapping("/comp/profileForm")
    public String profileForm() {
        return "/comp/jobopen/myPage/profileForm";
    }

}
