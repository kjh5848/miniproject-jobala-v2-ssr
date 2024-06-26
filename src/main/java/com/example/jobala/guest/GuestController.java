package com.example.jobala.guest;

import com.example.jobala._user.User;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala._user.UserService;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.Resume;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GuestController {

    private final HttpSession session;
    private final GuestQueryRepository guestRepository;
    private final GuestService guestService;
    private final UserJPARepository userJPARepository;
    private final GuestQueryRepository guestQueryRepository;
    private final GuestJPARepository guestJPARepository;
    private final JobopenJPARepository jobopenJPARepository;

    private final UserService userService;

    // DEL: mainForm 삭제

    @GetMapping("/guest/jobopenSearch")
    public String jobopenSearch(HttpServletRequest req, @RequestParam(value = "skills", defaultValue = "") String skills, GuestResponse.SearchDTO resDTO) {
        List<JobopenResponse.ListDTO> jobopenList = guestService.jobopenSearch(skills, resDTO);
        req.setAttribute("jobopenList", jobopenList);
        return "guest/jobSearch";
    }

    @GetMapping("/guest/jobSearch")
    public String jobSearch(HttpServletRequest req) {
        List<JobopenResponse.ListDTO> jobopenList = guestService.findAll();
        req.setAttribute("jobopenList", jobopenList);
        return "guest/jobSearch";
    }

    //이력서 관리 페이징
    @GetMapping("/guest/mngForm")
    public String mngForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        int userId = sessionUser.getId();
        List<Resume> resumeList = guestRepository.findResumeById(sessionUser.getId());
        req.setAttribute("resumeList", resumeList);
        return "guest/_myPage/mngForm";
    }
    @GetMapping("/guest/profileForm")
    public String profileForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User guestProfile = userService.guestInfo(sessionUser.getId());
        req.setAttribute("guestProfile", guestProfile);
        return "guest/_myPage/profileForm"; // 파일 확장자를 생략한 뷰의 경로
    }

    @PostMapping("/guest/updateProfile") // 주소 수정 필요!
    public String updateProfile(GuestRequest.GuestProfileUpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        guestService.guestUpdateProfile(reqDTO, sessionUser);
        return "redirect:/guest/profileForm";
    }
}