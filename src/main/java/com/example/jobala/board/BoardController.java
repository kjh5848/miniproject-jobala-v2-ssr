package com.example.jobala.board;

import com.example.jobala._user.User;
import com.example.jobala.reply.ReplyQueryRepository;
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
    private final BoardQueryRepository boardRepository;
    private final ReplyQueryRepository replyRepository;
    private final BoardService boardService;

    private final HttpSession session;

    @GetMapping("/board/{id}")
    public String boardDetailForm(@PathVariable int id, HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.글상세보기(id,sessionUser);


        List<ReplyResponse.ReplyDTO> replyList = replyRepository.findByBoardId(id, sessionUser);
        System.out.println("replyList = " + replyList);


        req.setAttribute("board", board);
        req.setAttribute("replyList", replyList);

        return "/board/detailForm";
    }


    @GetMapping("/board/mainForm")
    public String boardForm(HttpServletRequest req) {
       List<Board> boardList = boardService.글목록조회();
       req.setAttribute("boardList",boardList);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStringWithoutTime = sdf.format(date);
        req.setAttribute("currentDate", dateStringWithoutTime);

        return "/board/mainForm";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id, BoardRequest.UpdateDTO reqDTO) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글수정(id,sessionUser.getId(),reqDTO);
        return "redirect:/board/" + id ;

    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {
        Board board = boardService.글조회(id);
        request.setAttribute("board",board);
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
    public String save(BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println(1);
        // 게시물 저장 로직
        boardService.글쓰기(reqDTO,sessionUser);
        System.out.println();

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