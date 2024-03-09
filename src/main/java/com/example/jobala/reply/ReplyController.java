package com.example.jobala.reply;

import com.example.jobala._user.User;
import com.example.jobala.board.BoardRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final HttpSession session;

    @PostMapping("reply/save")
    public String repluSave(ReplyRequest.WriteDTO reqDTO) {
        System.out.println("reqDTO = " + reqDTO);
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println("sessionUser.getId() = " + sessionUser.getId());
        replyRepository.save(reqDTO, sessionUser);
        return "redirect:/board/" + reqDTO.getBoardId();
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
