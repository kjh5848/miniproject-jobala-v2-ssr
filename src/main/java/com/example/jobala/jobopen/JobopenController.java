package com.example.jobala.jobopen;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class JobopenController {

    @GetMapping("/comp/writeForm")
    public String writeForm() {
        return "/comp/jobOpen/writeForm";
    }

    @GetMapping("/comp/detailForm")
    public String detailForm() {
        return "/comp/jobopen/detailForm";
    }

    @GetMapping("/comp/updateForm")
    public String updateForm() {
        return "/comp/jobopen/updateForm";
    }

}
