package com.example.demo.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/board/boardForm")
    public String boardForm() {
        return "/board/boardForm";
    }

    @GetMapping("/board/boardDetail")
    public String boardDetail() {
        return "/board/boardDetail";
    }
}
