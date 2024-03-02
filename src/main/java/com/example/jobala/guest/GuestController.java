package com.example.jobala.guest;

import com.example.jobala.resume.Resume;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GuestController {

    private final HttpSession session;
    private final GuestRepository guestRepository;

    @GetMapping("/guest/jobSearch")
    public String jobSearch() {
        return "/guest/jobSearch";
    }

    @GetMapping("/guest/mngForm")
    public String mngForm(HttpServletRequest request) {
        List<Resume> resumeList = guestRepository.findResumeAll();
        request.setAttribute("resumeList", resumeList);
        return "/guest/_myPage/mngForm";
    }

    @GetMapping("/guest/profileForm")
    public String profileForm() {
        return "/guest/resume/myPage/profileForm";
    }

}
