package com.example.jobala.scrap;

import com.example.jobala.resume.Resume;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScrapController {
    private final ScrapRepository scrapRepository;


    @GetMapping("/comp/scrapForm")
    public String compScrapForm(HttpServletRequest req) {
        List<Resume> resumeList = scrapRepository.findResumeAll();
        req.setAttribute("resumeList", resumeList);
        return "/comp/_myPage/scrapForm";
    }

    @GetMapping("/comp/scrap")
    public void scrapResume(@RequestParam Long resumeId){

    }

    @GetMapping("/guest/scrapForm")
    public String guestScrapForm() {
        return "/guest/_myPage/scrapForm";
    }
}
