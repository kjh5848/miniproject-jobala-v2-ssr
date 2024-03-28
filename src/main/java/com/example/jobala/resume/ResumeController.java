package com.example.jobala.resume;

import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._user.User;
import com.example.jobala._user.UserQueryRepository;
import com.example.jobala.jobopen.JobopenQueryRepository;
import com.example.jobala.scrap.Scrap;
import com.example.jobala.scrap.ScrapJPARepository;
import com.example.jobala.scrap.ScrapQueryRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeQueryRepository resumeRepository;
    private final HttpSession session;
    private final UserQueryRepository userRepository;
    private final ScrapQueryRepository scrapRepository;
    private final JobopenQueryRepository jobopenRepository;
    private final ResumeService resumeService;
    private final ResumeJPARepository resumeJPARepository;
    private final ScrapJPARepository scrapJPARepository;

    //TODO: saveForm 삭제예정
    @GetMapping("/guest/resume/saveForm")
    public String saveForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        req.setAttribute("user", sessionUser);
        return "guest/resume/saveForm";
    }

    //이력서 업데이트
    @PostMapping("/guest/resume/{id}/update")
    public String update(@PathVariable Integer id, ResumeRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.이력서수정(id, reqDTO,sessionUser.getId());
        System.out.println("이력서 수정 실행");
        return "redirect:/guest/mngForm";
    }

    // TODO: 글조회로 변경예정
    @GetMapping("/guest/resume/{id}/updateForm")
    public String updateForm(@PathVariable Integer id, HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.DetailDTO respDTO = resumeService.이력서보기(id, sessionUser);

        req.setAttribute("user", sessionUser);
        req.setAttribute("resume", respDTO);
        return "guest/resume/updateForm";
    }

    //이력서 상세보기
    @GetMapping("/guest/resume/{id}")
    public String detailForm(@PathVariable Integer id, HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ResumeResponse.DetailDTO respDTO = resumeService.이력서보기(id, sessionUser);

        req.setAttribute("resume", respDTO);
        return "guest/resume/detailForm";
    }

    //이력서 등록
    @PostMapping("/guest/resume/save")
    public String save(ResumeRequest.SaveDTO resumeSaveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.이력서등록(resumeSaveDTO, sessionUser);
        return "redirect:/guest/mngForm";
    }

    //이력서 삭제
    @PostMapping("/resume/{id}/delete")
    public String delete(@PathVariable int id, ResumeRequest.DeleteDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.이력서삭제(id, reqDTO.getId());
        return "redirect:/guest/mngForm";
    }
}