package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/comp/mainForm")
    public String compMain() {
        return "/comp/mainForm"; // index.mustache 템플릿 파일을 반환
    }

    @GetMapping("/comp/Mypage")
    public String compMypage() {
        return "/comp/MypageForm"; // index.mustache 템플릿 파일을 반환
    }

    @GetMapping("/comp/supportManagement")
    public String supportManagement() {
        return "/comp/supportManagement"; // index.mustache 템플릿 파일을 반환
    }

    @GetMapping("/comp/employmentAddForm")
    public String employmentAddForm() {
        return "/comp/employmentAddForm"; // index.mustache 템플릿 파일을 반환
    }

    @GetMapping("/comp/boardForm")
    public String boardForm() {
        return "/comp/boardForm"; // index.mustache 템플릿 파일을 반환
    }

    @GetMapping("/comp/logout")
    public String logout() {
        return "compMain"; // index.mustache 템플릿 파일을 반환
    }
}