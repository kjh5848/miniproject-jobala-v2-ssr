package com.example.jobala.comp;


import com.example.jobala._user.User;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala.apply.ApplyJPARepository;
import com.example.jobala.apply.ApplyRequest;
import com.example.jobala.apply.ApplyResponse;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;
import com.example.jobala.resume.ResumeRequest;
import com.example.jobala.resume.ResumeResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompController {

    private final HttpSession session;
    private final CompQueryRepository compQueryRepository;
    private final ApplyJPARepository applyJPARepository;
    private final CompService compService;

    //인재 이력서 검색하기
    @GetMapping("/comp/resumeSearch")
    public String jobopenSearch(HttpServletRequest req, @RequestParam(value = "skills", defaultValue = "") String skills, CompResponse.SearchDTO resDTO) {
        List<ResumeResponse.ListDTO> resumeList = compService.searchResumes(skills, resDTO);
        req.setAttribute("resumeList", resumeList);
        return "comp/scoutList";
    }

    //인재 명단 목록
    @GetMapping("/comp/scoutList")
    public String scoutList(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ResumeResponse.ListDTO> resumeList = compService.listAllResumes();
        req.setAttribute("resumeList", resumeList);
        return "comp/scoutList";
    }

    // DEL: getResumeList 삭제

    //마이페이지 - 공고 관리
    @GetMapping("/comp/mngForm")
    public String mngForm(HttpServletRequest req) {
        User sessionUser = (User) req.getSession().getAttribute("sessionUser");
        List<JobopenResponse.DTO> jobopenList = compService.searchjobopenList(sessionUser.getId());
        req.setAttribute("jobopenList", jobopenList);
        return "comp/_myPage/mngForm";
    }

    // 마이페이지 - 프로필관리
    @GetMapping("/comp/profileForm")
    public String profileForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User compProfile = compService.getCompanyProfile(sessionUser.getId());
        req.setAttribute("compProfile", compProfile);
        return "comp/_myPage/profileForm";
    }

    // 마이페이지 프로필 업데이트
    @PostMapping("/comp/updateProfile") // 주소 수정 필요!
    public String updateProfile(CompRequest.CompProfileUpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        compService.compUpdateProfile(reqDTO, sessionUser);
        return "redirect:/comp/profileForm";
    }
}