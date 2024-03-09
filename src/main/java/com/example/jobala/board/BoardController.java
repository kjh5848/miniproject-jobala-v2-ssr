package com.example.jobala.board;

import com.example.jobala._user.User;
import com.example.jobala.reply.ReplyRepository;
import com.example.jobala.reply.ReplyResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class BoardController {
private final BoardRepository boardRepository;
//private final ReplyRepository replyRepository;
    private final HttpSession session;

    @GetMapping("/board/mainForm")
    public String boardForm(HttpServletRequest req) {
        List<BoardResponse.MainDetailDTO> respDTO = boardRepository.findAllWithUser();
        req.setAttribute("boardList", respDTO);
        return "/board/mainForm";
    }

    @GetMapping("/board/{id}/detailForm")
    public String boardDetail(@PathVariable int id, HttpServletRequest req, ReplyResponse.ReplyListDTO replyListDTO) {

        BoardResponse.BoardDetailDTO respDTO = boardRepository.findById(id);
        //List<ReplyResponse.ReplyListDTO> replyList = replyRepository.replyList(id);

        // 댓글 주인 여부체크
        User sessionUser = (User) session.getAttribute("sessionUser");
        Boolean isSameCheck = sessionUser != null && sessionUser.getUsername().equals(replyListDTO.getUsername());

        req.setAttribute("board", respDTO);
        //req.setAttribute("replyList", replyList);
        req.setAttribute("isSameCheck", isSameCheck);

        return "/board/DetailForm";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id, BoardRequest.UpdateDTO requestDTO){

        User sessionUser = (User) session.getAttribute("sessionUser");
        if(sessionUser == null){
            return "redirect:/loginForm";
        }
        BoardResponse.BoardDetailDTO board = boardRepository.findById(id);
        boardRepository.update(requestDTO, id);

        return "redirect:/board/mainForm";
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
    public String saveForm(){
        return"/board/saveForm";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO requestDTO, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/loginForm";
        }

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

        // 게시물 저장 로직
        boardRepository.save(requestDTO, sessionUser.getId());

        // 메인 폼으로 리다이렉트
        return "redirect:/board/mainForm";
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
