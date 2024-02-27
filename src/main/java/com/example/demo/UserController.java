package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/user/joinForm")
    public String joinForm() {
        return "/user/joinForm";
    }

    @GetMapping("/user/loginForm")
    public String loginForm() {
        return "/layout/loginForm";
    }

    @GetMapping("/user/searchEmployAd")
    public String as() {
        return "/user/searchEmployAd";
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

    @GetMapping("/user/resumeWrite")
    public String resumeWrite() {
        return "/user/resumeWrite";
    }

    @GetMapping("/user/resume2")
    public String resume2() {
        return "/user/resume2";
    }

    @GetMapping("/user/supportManagement")
    public String supportManagement() {
        return "/user/supportManagement";
    }

    @GetMapping("/user/clippedCompany")
    public String clippedCompany() {
        return "/user/clippedCompany";
    }

    @GetMapping("/logout")
    public String logout() {
        return "index";
    }
}
