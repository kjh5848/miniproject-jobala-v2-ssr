package com.example.jobala.guest;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GuestController {

    private final HttpSession session;
    private final GuestRepository guestRepository;

    // DEL: mainForm 삭제
    @GetMapping("/guest/jobopenSearch")
    public String jobopenSearch(HttpServletRequest req, @RequestParam(value = "skills", defaultValue = "") String skills, GuestResponse.SearchDTO resDTO) {
        // [,]를 없애기위해 substring
        String slicedSkills = skills.substring(1, skills.length() - 1);
        System.out.println(slicedSkills);
        System.out.println(resDTO);
        List<Jobopen> jobopenList = guestRepository.findAll(slicedSkills, resDTO);

        req.setAttribute("jobopenList", jobopenList);

        return "/guest/jobSearch";
    }

    @GetMapping("/guest/jobSearch")
    public String jobSearch(HttpServletRequest req) {
        List<Jobopen> jobopenList = guestRepository.findByJoboopenAll();
        req.setAttribute("jobopenList", jobopenList);
        return "/guest/jobSearch";
    }


    @GetMapping("/guest/mngForm")
    public String mngForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        int userId = sessionUser.getId();
        System.out.println(userId);
        List<Resume> resumeList = guestRepository.findResumeById(sessionUser.getId());
        request.setAttribute("resumeList", resumeList);
        return "/guest/_myPage/mngForm";
    }

    @GetMapping("/guest/profileForm")
    public String profileForm() {
        return "/guest/_myPage/profileForm";
    }

    @GetMapping("/applyStatusForm")
    public String applyStatusForm() {
        return "/guest/_myPage/applyStatusForm";
    }

}
