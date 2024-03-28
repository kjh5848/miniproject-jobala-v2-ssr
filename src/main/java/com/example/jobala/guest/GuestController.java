package com.example.jobala.guest;

import com.example.jobala._user.User;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala._user.UserService;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.jobopen.JobopenQueryRepository;
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

import javax.swing.*;
import java.util.List;
import java.util.Optional;

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


    //TODO: 서비스 만들기
    @GetMapping("/guest/jobopenSearch")
    public String jobopenSearch(HttpServletRequest req, @RequestParam(value = "skills", defaultValue = "") String skills, GuestResponse.SearchDTO resDTO) {
        List<JobopenResponse.ListDTO> jobopenList = guestService.jobopenSearch(skills, resDTO);
        req.setAttribute("jobopenList", jobopenList);
        return "guest/jobSearch";
    }

    //TODO: 서비스 만들기
    @GetMapping("/guest/jobSearch")
    public String jobSearch(HttpServletRequest req) {
        List<JobopenResponse.ListDTO> jobopenList = guestService.findAll();
        req.setAttribute("jobopenList", jobopenList);
        return "guest/jobSearch";
    }

    //TODO: 서비스 만들기
    @GetMapping("/guest/mngForm")
    public String mngForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Resume> resumeList = guestService.findResumeByUserId(sessionUser.getId());
        req.setAttribute("resumeList", resumeList);
        return "guest/_myPage/mngForm";
    }

    //TODO: 서비스 만들기
    @GetMapping("/guest/profileForm")
    public String profileForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        User guestProfile = userService.guestInfo(sessionUser.getId());
        req.setAttribute("guestProfile", guestProfile);
        return "guest/_myPage/profileForm"; // 파일 확장자를 생략한 뷰의 경로
    }

    @PostMapping("/guest/updateProfile") // 주소 수정 필요!
    public String updateProfile(@RequestParam MultipartFile imgFilename, GuestRequest.GuestProfileUpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        String img = String.valueOf(imgFilename);
        guestService.guestUpdateProfile(reqDTO, sessionUser);
        return "redirect:/guest/profileForm";
    }
}