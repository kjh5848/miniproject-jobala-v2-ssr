package com.example.jobala.board;

import com.example.jobala.apply.ApplyRequest;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class BoardController {
private final BoardRepository boardRepository;

    @GetMapping("/board/mainForm")
    public String boardForm(HttpServletRequest req) {
        List<BoardResponse.DetailDTO> respDTO = boardRepository.findAllWithUser();
        req.setAttribute("boardList", respDTO);
        return "/board/mainForm";
    }

    @GetMapping("/board/{id}/detailForm")
    public String boardDetail(@PathVariable int id, HttpServletRequest req) {

        BoardResponse.BoardDetailDTO responseDTO = boardRepository.findById(id);

        req.setAttribute("board", responseDTO);
        return "board/DetailForm";
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
