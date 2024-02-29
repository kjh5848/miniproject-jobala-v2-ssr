package com.example.jobala.jobOpen;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class JobOpenController {

    @GetMapping("/jobOpen/writeForm")
    public String scoutList(){
        return "/comp/jobOpen/writeForm";
    }
}
