package com.example.jobala.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class BoardController {


    @GetMapping("/board/mainForm")
    public String boardForm() {
        return "/board/mainForm";
    }

    @GetMapping("/board/DetailForm")
    public String boardDetail() {
        return "/board/DetailForm";
    }

    @GetMapping("/board/updateForm")
    public String updateForm() {
        return "/board/updateForm";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "/board/saveForm";
    }
}
