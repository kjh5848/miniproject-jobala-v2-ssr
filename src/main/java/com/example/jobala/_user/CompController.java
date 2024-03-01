package com.example.jobala._user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CompController {


    @GetMapping("/comp/joinForm")
    public String loginForm() {
        return "/comp/joinForm";
    }

    @GetMapping("/comp/scoutList")
    public String scoutList() {
        return "/comp/scoutList";
    }
}
