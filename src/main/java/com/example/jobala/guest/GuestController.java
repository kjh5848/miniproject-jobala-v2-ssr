package com.example.jobala.guest;

import com.example.jobala._user.User;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala.jobopen.Jobopen;
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
    private final JobopenJPARepository jobopenJPARepository;
    private final GuestJPARepository guestJPARepository;

    // DEL: mainForm 삭제


    @GetMapping("/guest/jobopenSearch")
    public String jobopenSearch(HttpServletRequest req, @RequestParam(value = "skills", defaultValue = "") String skills, GuestResponse.SearchDTO resDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        // [,]를 없애기위해 substring
        String slicedSkills = skills.substring(1, skills.length() - 1);

        List<User> userList = userJPARepository.findAll();
        req.setAttribute("jobopenList", userList);

        return "guest/jobSearch";
    }


    @GetMapping("/guest/jobSearch")
    public String jobSearch(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Jobopen> jobopenList = jobopenJPARepository.findAll();
        req.setAttribute("jobopenList", jobopenList);
        return "guest/jobSearch";
    }


    @GetMapping("/guest/mngForm")
    public String mngForm(HttpServletRequest req,@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "3")int size) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Page<Resume> resumePage = guestService.resumesFindAll(page, size);
        req.setAttribute("resumeList",resumePage.getContent());
        req.setAttribute("first", page == 0 ? true:false);
        req.setAttribute("last",page < resumePage.getTotalPages() -1);
        req.setAttribute("previousPage",page -1);
        req.setAttribute("nextPage",page +1);

        return "guest/_myPage/mngForm";
    }

    @GetMapping("/guest/profileForm")
    public String profileForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        User guestProfile = userJPARepository.findById(sessionUser.getId()).get();
        req.setAttribute("guestProfile", guestProfile);
        return "guest/_myPage/profileForm"; // 파일 확장자를 생략한 뷰의 경로
    }

    @PostMapping("/guest/updateProfile") // 주소 수정 필요!
    public String updateProfile(@RequestParam MultipartFile imgFilename, GuestRequest.GuestProfileUpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        System.out.println("reqDTO = " + reqDTO);
        System.out.println("imgFilename = " + imgFilename);
        String img = String.valueOf(imgFilename);
        System.out.println("img = " + img);
        guestService.guestUpdateProfile(reqDTO, sessionUser);
        return "redirect:/guest/profileForm";
    }
}