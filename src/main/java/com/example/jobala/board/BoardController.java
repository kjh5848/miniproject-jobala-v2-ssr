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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    private final HttpSession session;

    @GetMapping("/board/{id}")
    public String boardDetailForm(@PathVariable int id, HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        System.out.println("id = " + id);

        BoardResponse.BoardDetailDTO boardDetailDTO = boardRepository.findById(id);
        System.out.println(boardDetailDTO);

        List<ReplyResponse.ReplyDTO> replyList = replyRepository.findByBoardId(id, sessionUser);
        System.out.println("replyList = " + replyList);
        boardDetailDTO.isOwner(sessionUser);

        req.setAttribute("board", boardDetailDTO);
        req.setAttribute("replyList", replyList);

        return "/board/detailForm";
    }


    @GetMapping("/board/mainForm")
    public String boardForm(HttpServletRequest req) {
        List<BoardResponse.MainDetailDTO> respDTO = boardRepository.findAllWithUser();
        req.setAttribute("boardList", respDTO);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStringWithoutTime = sdf.format(date);
        req.setAttribute("currentDate", dateStringWithoutTime);

        return "/board/mainForm";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id, BoardRequest.UpdateDTO requestDTO) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        BoardResponse.BoardDetailDTO board = boardRepository.findById(id);
        boardRepository.update(requestDTO, id);
        return "redirect:/board/" + id ;

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
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        return "/board/saveForm";
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
    public String delete(@PathVariable int id) {
        System.out.println(id);
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) { // 401
            return "redirect:/loginForm";
        }

        boardRepository.deleteById(id);
        System.out.println(id);
        return "redirect:/board/mainForm";
    }
}