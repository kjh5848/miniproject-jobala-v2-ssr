package com.example.jobala.scrap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ScrapController {


    @GetMapping("/comp/scrapForm")
    public String compScrapForm() {
        return "/comp/jobopen/myPage/scrapForm";
    }

    @GetMapping("/guest/scrapForm")
    public String guestScrapForm() {
        return "/guest/resume/myPage/scrapForm";
    }
}
