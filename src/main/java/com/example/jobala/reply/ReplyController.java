package com.example.jobala.reply;

import com.example.jobala._user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final HttpSession session;
    private final ReplyService replyService;


    @PostMapping("reply/save")   // 주소 수정 필요
    public String replySave(ReplyRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.replySave(reqDTO,sessionUser);
        return "redirect:/board/" + reqDTO.getBoardId();
    }

    //댓글 삭제
    @PostMapping("reply/{replyId}/delete")  // 주소 수정 필요
    public String deleteReply(@PathVariable Integer replyId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.replyDelete(replyId,sessionUser.getId());

        return "redirect:/board/"+sessionUser.getId();
    }
}