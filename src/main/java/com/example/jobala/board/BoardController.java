package com.example.jobala.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
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
