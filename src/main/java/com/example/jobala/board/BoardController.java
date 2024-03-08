package com.example.jobala.board;

import com.example.jobala._user.User;
import com.example.jobala.apply.ApplyRequest;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class BoardController {
private final BoardRepository boardRepository;
    private final HttpSession session;

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

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id, BoardRequest.UpdateDTO requestDTO){

        User sessionUser = (User) session.getAttribute("sessionUser");
        if(sessionUser == null){
            return "redirect:/loginForm";
        }
        BoardResponse.BoardDetailDTO board = boardRepository.findById(id);
        boardRepository.update(requestDTO, id);

        return "redirect:/board/" + id + "/detailForm";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

        BoardResponse.BoardDetailDTO board = boardRepository.findById(id);
        request.setAttribute("board", board);

        return "board/updateForm";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "/board/saveForm";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) { // 401
            return "redirect:/loginForm";
        }

        boardRepository.deleteById(id);

        return "redirect:/board/mainForm";
    }
}
