package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String main() {
        return "index"; // index.mustache 템플릿 파일을 반환
    }

    @GetMapping("/user/mypage")
    public String userMypage() {
        return "/user/mypage"; // index.mustache 템플릿 파일을 반환
    }

    @GetMapping("/user/resume")
    public String resume() {
        return "/user/resume"; // index.mustache 템플릿 파일을 반환
    }

    @GetMapping("/user/supportManagement")
    public String supportManagement() {
        return "/user/supportManagement"; // index.mustache 템플릿 파일을 반환
    }

    @GetMapping("/user/clippedCompany")
    public String clippedCompany() {
        return "/user/clippedCompany"; // index.mustache 템플릿 파일을 반환
    }

    @GetMapping("/user/boardForm")
    public String boardForm() {
        return "/user/boardForm"; // index.mustache 템플릿 파일을 반환
    }

    @GetMapping("/logout")
    public String logout() {
        return "index"; // index.mustache 템플릿 파일을 반환
    }

}
