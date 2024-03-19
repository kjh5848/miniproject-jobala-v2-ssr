package com.example.jobala.scrap;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
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
    public String scrapResume(ScrapRequest.CompScrap reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

        // 스크랩 했는지 확인 (null -> Scrap 안함, not null -> Scrap 함)
        Scrap scrap = null;
        int resumeId = reqDTO.getResumeId();
        int userId = sessionUser.getId();
        try {
            scrap = scrapRepository.findCompScrapById(resumeId, userId);
        } catch (Exception e) {
        }
        if (scrap == null) {
            scrapRepository.compScrapSave(resumeId, userId);
        }
        if (scrap != null) {
            scrapRepository.compScrapDelete(scrap.getId());
        }

        return "redirect:/guest/resume/" + reqDTO.getResumeId();
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


    @PostMapping("/guest/scrap")
    public String scrapJobopen(ScrapRequest.GuestScrap reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

        // 스크랩 했는지 확인 (null -> Scrap 안함, not null -> Scrap 함)
        Scrap scrap = null;
        int jobopenId = reqDTO.getJobopenId();
        int userId = sessionUser.getId();
        try {
            scrap = scrapRepository.findGuestScrapById(jobopenId, userId);
        } catch (Exception e) {
        }
        if (scrap == null) {
            scrapRepository.guestScrapSave(jobopenId, userId);
        }
        if (scrap != null) {
            scrapRepository.guestScrapDelete(scrap.getId());
        }
        return "redirect:/comp/jobopen/" + reqDTO.getJobopenId();
    }
}
