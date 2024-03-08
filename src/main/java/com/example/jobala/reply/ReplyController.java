package com.example.jobala.reply;

import com.example.jobala._user.User;
import com.example.jobala.board.BoardRepository;
import com.example.jobala.board.BoardResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final HttpSession session;


    @GetMapping("/board/{id}/detailForm")
    public String boardDetail(@PathVariable int id, HttpServletRequest req, ReplyResponse.ReplyListDTO replyListDTO) {

        BoardResponse.BoardDetailDTO responseDTO = boardRepository.findById(id);
        List<ReplyResponse.ReplyListDTO> replyList = replyRepository.replyList(id);

        // 댓글 주인 여부체크
        User sessionUser = (User) session.getAttribute("sessionUser");
        Boolean isSameCheck = sessionUser != null && sessionUser.getUsername().equals(replyListDTO.getUsername());

        req.setAttribute("board", responseDTO);
        req.setAttribute("replyList", replyList);
        req.setAttribute("isSameCheck", isSameCheck);

        return "/board/DetailForm";
    }


    @PostMapping("/reply/{id}/delete")
    public String deleteReply(@PathVariable int id, HttpSession session) {
        // 댓글의 username과 세션의 username 비교해서 같으면 삭제 가능
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

        Reply reply = replyRepository.findById(id);

        if (reply == null) {
            return "redirect:/board/DetailForm";
        }

        if (!sessionUser.getUsername().equals(reply.getUsername())) {
            return "redirect:/loginForm";
        }

        replyRepository.deleteById(id);

        return "redirect:/board/" + reply.getBoardId() + "/detailForm";
    }
}
