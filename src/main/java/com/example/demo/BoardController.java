package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/")
    public String main() {
        return "index1"; // index.mustache 템플릿 파일을 반환
    }
}