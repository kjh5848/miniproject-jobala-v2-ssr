package com.example.jobala.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    @GetMapping("/board")
    public String boardForm() {
        return "/board/boardForm";
    }

    @GetMapping("/board/boardForm")
    public String boardDetail() {
        return "/board/boardDetail";
    }
}
