package com.example.jobala.reply;

import com.example.jobala._user.User;
import com.example.jobala.board.BoardRepository;
import com.example.jobala.board.BoardResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final HttpSession session;


    @PostMapping("/reply/save")
    public String save(ReplyRequest.SaveDTO reqDTO, HttpServletRequest req) {
        System.out.println(reqDTO);

        // 1. 인증 체크
        User sessionUser = (User) req.getSession().getAttribute("sessionUser");
        System.out.println("sessionUser:" + sessionUser);
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

        // reqDTO에서 id를 가져옵니다. (예를 들어, getId() 메소드가 있다고 가정)
        int id = reqDTO.getBoardId();

        // 이제 'id' 변수를 사용해 boardRepository를 호출할 수 있습니다.
        BoardResponse.BoardDetailDTO respDTO = boardRepository.findById(id);
        List<ReplyResponse.ReplyListDTO> replyList = replyRepository.replyList(id);
        replyRepository.save(reqDTO, sessionUser.getUsername());

        req.setAttribute("board", respDTO);
        return "detailForm";
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
