package com.example.jobala.scrap;

import com.example.jobala._user.User;
import com.example.jobala.resume.Resume;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScrapController {
    private final ScrapRepository scrapRepository;
    private final HttpSession session;


    @GetMapping("/comp/scrapForm")
    public String compScrapForm(HttpServletRequest req) {
        List<Resume> resumeList = scrapRepository.findResumeAll();
        req.setAttribute("resumeList", resumeList);
        return "/comp/_myPage/scrapForm";
    }

    @PostMapping("/comp/scrap")
    public String scrapResume(ScrapRequest.CompScrap reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        int resumeId = reqDTO.getResumeId();
        int userId = sessionUser.getId();
        // 스크랩 했는지 확인 (null -> Scrap 안함, not null -> Scrap 함)
        Scrap scrap = null;
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

        return "redirect:/guest/resume/"+reqDTO.getResumeId();
    }

    @GetMapping("/guest/scrapForm")
    public String guestScrapForm() {
        return "/guest/_myPage/scrapForm";
    }
}
