package com.example.jobala.reply;

import com.example.jobala.board.BoardRepository;
import com.example.jobala.board.BoardResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    @GetMapping("/board/{id}/detailForm")
    public String boardDetail(@PathVariable int id, HttpServletRequest req) {

        BoardResponse.BoardDetailDTO responseDTO = boardRepository.findById(id);
        List<ReplyResponse.ReplyListDTO> replyList = replyRepository.replyList(id);
        req.setAttribute("replyList", replyList);

        req.setAttribute("board", responseDTO);
        return "board/DetailForm";
    }
}
