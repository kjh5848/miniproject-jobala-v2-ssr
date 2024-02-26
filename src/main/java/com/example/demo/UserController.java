package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/user/joinForm")
    public String joinForm() {
        return "/user/joinForm";
    }

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/user/mypage")
    public String userMypage() {
        return "/user/mypage";
    }

    @GetMapping("/user/resume")
    public String resume() {
        return "/user/resume";
    }

    @GetMapping("/user/supportManagement")
    public String supportManagement() {
        return "/user/supportManagement";
    }

    @GetMapping("/user/clippedCompany")
    public String clippedCompany() {
        return "/user/clippedCompany";
    }

    @GetMapping("/user/boardForm")
    public String boardForm() {
        return "/user/boardForm";
    }

    @GetMapping("/logout")
    public String logout() {
        return "index";
    }

}
