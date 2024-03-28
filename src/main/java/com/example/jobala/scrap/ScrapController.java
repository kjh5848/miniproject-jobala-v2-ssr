package com.example.jobala.scrap;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeResponse;
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
    private final HttpSession session;
    private final ScrapService scrapService;

    //기업의 스크랩 목록
    @GetMapping("/comp/scrapForm")
    public String compScrapForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ResumeResponse.ScrapDTO> respDTO =  scrapService.scrapResumeBycomp(sessionUser.getId());
        req.setAttribute("resumeList", respDTO);
        return "comp/_myPage/scrapForm";
    }

    //기업 스크랩
    @PostMapping("/comp/scrap")
    public String scrapResume(ScrapRequest.CompScrapDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        // 스크랩 했는지 확인 (null -> Scrap 안함, not null -> Scrap 함)
        scrapService.scrapByComp(reqDTO, sessionUser);
        return "redirect:/guest/resume/" + reqDTO.getResumeId();
    }

    // 개인의 스크랩 목록
    @GetMapping("/guest/scrapForm")
    public String guestScrapForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<JobopenResponse.ScrapDTO> respDTO = scrapService.scrapJobopenByGuest(sessionUser.getId());
        req.setAttribute("jobopenList", respDTO);

        return "guest/_myPage/scrapForm";
    }

    //개인 스크랩
    @PostMapping("/guest/scrap")
    public String scrapJobopen(ScrapRequest.GuestScrap reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        // 스크랩 했는지 확인 (null -> Scrap 안함, not null -> Scrap 함)
        scrapService.scrapByGuest(reqDTO, sessionUser);
        return "redirect:/comp/jobopen/" + reqDTO.getJobopenId();
    }

}
