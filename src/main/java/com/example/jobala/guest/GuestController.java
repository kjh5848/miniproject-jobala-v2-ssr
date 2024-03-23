package com.example.jobala.guest;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.Resume;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GuestController {

    private final HttpSession session;
    private final GuestQueryRepository guestRepository;
    private final GuestService guestService;

    // DEL: mainForm 삭제
    @GetMapping("/guest/jobopenSearch")
    public String jobopenSearch(HttpServletRequest req, @RequestParam(value = "skills", defaultValue = "") String skills, GuestResponse.SearchDTO resDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        // [,]를 없애기위해 substring
        String slicedSkills = skills.substring(1, skills.length() - 1);
        System.out.println(slicedSkills);
        System.out.println(resDTO);
        List<JobopenResponse.ListDTO> jobopenList = guestRepository.findAll(slicedSkills, resDTO);
        req.setAttribute("jobopenList", jobopenList);

        return "/guest/jobSearch";
    }

    @GetMapping("/guest/jobSearch")
    public String jobSearch(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        List<JobopenResponse.ListDTO> jobopenList = guestRepository.findByJoboopenAll();
        req.setAttribute("jobopenList", jobopenList);
        return "/guest/jobSearch";
    }


    @GetMapping("/guest/mngForm")
    public String mngForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        int userId = sessionUser.getId();
        System.out.println(userId);
        List<Resume> resumeList = guestRepository.findResumeById(sessionUser.getId());
        req.setAttribute("resumeList", resumeList);
        return "/guest/_myPage/mngForm";
    }

    @GetMapping("/guest/profileForm")
    public String profileForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        List<GuestResponse.GuestProfileDTO> guestProfile = guestRepository.findProfileByUserId(sessionUser.getId());
        req.setAttribute("guestProfile", guestProfile);
        return "guest/_myPage/profileForm"; // 파일 확장자를 생략한 뷰의 경로
    }

    @PostMapping("/guest/updateProfile")
    public String updateProfile(@RequestParam MultipartFile imgFilename, GuestRequest.GuestProfileUpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        System.out.println("reqDTO = " + reqDTO);
        System.out.println("imgFilename = " + imgFilename);
        String img = String.valueOf(imgFilename);
        System.out.println("img = " + img);
        guestService.프로필업데이트(reqDTO, sessionUser);
        return "redirect:/guest/profileForm";
    }
}