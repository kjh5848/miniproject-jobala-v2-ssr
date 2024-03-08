package com.example.jobala.guest;

import com.example.jobala._user.User;
import com.example.jobala.apply.ApplyRequest;
import com.example.jobala.apply.ApplyResponse;
import com.example.jobala.board.BoardResponse;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public String mngForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        int userId = sessionUser.getId();
        System.out.println(userId);
        List<Resume> resumeList = guestRepository.findResumeById(sessionUser.getId());
        req.setAttribute("resumeList", resumeList);
        return "/guest/_myPage/mngForm";
    }

    @GetMapping("/guest/profileForm")
    public String profileForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        int userId = sessionUser.getId();
        List<GuestResponse.GuestProfileDTO> guestProfile = guestRepository.findProfileByUserId(sessionUser.getId());
        req.setAttribute("guestProfile", guestProfile);
        return "/guest/_myPage/profileForm";
    }

    @PostMapping("/guest/updateProfile")
    public String updateProfile(@ModelAttribute GuestResponse.GProfileUpdateDTO profileDto, HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if(sessionUser == null) {
            return "redirect:/login";
        }
        profileDto.setId(sessionUser.getId());
        guestRepository.updateProfile(profileDto);
        return "redirect:/guest/profileForm";
    }
}

