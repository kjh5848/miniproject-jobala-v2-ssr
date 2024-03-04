package com.example.jobala.guest;

import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GuestController {

    private final HttpSession session;
    private final GuestRepository guestRepository;

    @GetMapping("/guest/jobSearch/{id}")
    public String mainForm(@PathVariable int id, HttpServletRequest req) {
        Jobopen jobopen = guestRepository.findByIdWithUser(id);
        req.setAttribute("jobopen", jobopen);
        return "redirect:/comp/{id}";
    }

    @GetMapping("/guest/jobSearch")
    public String jobSearch(HttpServletRequest req) {

        List<Jobopen> jobopenList = guestRepository.findAll();
        System.out.println("jobopenList = " + jobopenList);
        req.setAttribute("jobopenList", jobopenList);
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
        return "/guest/_myPage/profileForm";
    }

}
