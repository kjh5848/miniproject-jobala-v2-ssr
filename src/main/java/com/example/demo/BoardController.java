package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;

public class BoardController {
    @GetMapping("/board/boardForm")
    public String boardForm() {
        return "board/boardForm";
    }

    @GetMapping("/board/boardDetail")
    public String boardDetail() {
        return "/board/boardDetail";
    }
}
