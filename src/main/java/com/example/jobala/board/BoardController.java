package com.example.jobala.board;

import com.example.jobala._user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    private final HttpSession session;

    @GetMapping("/board/{id}")
    public String boardDetailForm(@PathVariable int id, HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO board = boardService.글상세보기(id,sessionUser);

        req.setAttribute("board", board);


        return "board/detailForm";
    }


    @GetMapping("/board/mainForm")
    public String boardForm(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size, HttpServletRequest req) {
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<Board> boardPage = boardService.글목록조회(pageable);

        req.setAttribute("boardList",boardPage.getContent());
        req.setAttribute("first", page == 0 ? true:false);
        req.setAttribute("last",page < boardPage.getTotalPages() -1);
        req.setAttribute("previousPage",page -1);
        req.setAttribute("nextPage",page +1);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStringWithoutTime = sdf.format(date);
        req.setAttribute("currentDate", dateStringWithoutTime);

        return "board/mainForm";
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

    //TODO: saveForm삭제 예정
    @GetMapping("/board/saveForm")
    public String saveForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        return "board/saveForm";
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
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) { // 401
            return "redirect:/loginForm";
        }
        boardService.글삭제하기(id, sessionUser.getId());
        return "redirect:/board/mainForm";
    }
}