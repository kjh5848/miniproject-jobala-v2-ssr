package com.example.jobala.reply;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyRepository replyRepository;

    @GetMapping("/board/DetailForm/{id}")
    public String replyList(HttpServletRequest req){
        List<ReplyResponse.ReplyListDTO> replyList = replyRepository.replyList();
        req.setAttribute("replyList", replyList);
        return "board/DetailForm";
    }
}
