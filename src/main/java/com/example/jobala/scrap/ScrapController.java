package com.example.jobala.scrap;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScrapController {
    private final ScrapQueryRepository scrapRepository;
    private final HttpSession session;
    private final ScrapService scrapService;


    @GetMapping("/comp/scrapForm")
    public String compScrapForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        List<Resume> resumeList = scrapRepository.findResumeAll(sessionUser.getId());
        req.setAttribute("resumeList", resumeList);

        return "/comp/_myPage/scrapForm";
    }

    @PostMapping("/comp/scrap")
    public String scrapResume(ScrapRequest.CompScrapDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        // 스크랩 했는지 확인 (null -> Scrap 안함, not null -> Scrap 함)
        scrapService.회사가스크랩(reqDTO, sessionUser);

        return "redirect:/guest/resume/" + reqDTO.getResumeId();
    }

    @PostMapping("/guest/scrap")
    public String scrapJobopen(ScrapRequest.GuestScrap reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

        // 스크랩 했는지 확인 (null -> Scrap 안함, not null -> Scrap 함)
        scrapService.게스트가스크랩(reqDTO, sessionUser);
        return "redirect:/comp/jobopen/" + reqDTO.getJobopenId();
    }


    @GetMapping("/guest/scrapForm")
    public String guestScrapForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

        List<Jobopen> jobopenList = scrapRepository.findJobopenAll(sessionUser.getId());
        req.setAttribute("jobopenList", jobopenList);

        return "/guest/_myPage/scrapForm";
    }

}
