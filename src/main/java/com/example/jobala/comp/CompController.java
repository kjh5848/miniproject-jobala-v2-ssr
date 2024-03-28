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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompController {

    private final HttpSession session;
    private final CompQueryRepository compRepository;
    private final ApplyJPARepository applyJPARepository;
    private final CompService compService;
    private final UserJPARepository userJPARepository;
    private final ResumeJPARepository resumeJPARepository;

    @GetMapping("/comp/resumeSearch")
    public String jobopenSearch(HttpServletRequest req, @RequestParam(value = "skills", defaultValue = "") String skills, CompResponse.SearchDTO resDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        // [,]를 없애기위해 substring
        String slicedSkills = skills.substring(1, skills.length() - 1);
        List<ResumeResponse.ListDTO> resumeList = compRepository.findAll(slicedSkills, resDTO);

        req.setAttribute("resumeList", resumeList);

        return "comp/scoutList";
    }

    @GetMapping("/comp/scoutList")
    public String scoutList(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ResumeResponse.ListDTO> resumeList = compRepository.findResumeAll();

        req.setAttribute("resumeList", resumeList);
        return "comp/scoutList";
    }

    @GetMapping("/comp/scoutList/{id}")
    public String scoutDetail(@PathVariable Integer id, HttpServletRequest req) {
        //1. 기업 정보 꺼내오기 (인증 체크)
        User sessionUser = (User) session.getAttribute("sessionUser");
        //2. 인재 명단에서 인재 클릭 시 이력서로 들어가지는 로직 짜기
        Resume resume = (Resume) compRepository.findResumeById(id);

        req.setAttribute("sessionUser", sessionUser);
        req.setAttribute("resume", resume);

        return "geust/resume/detailForm";
    }

//    @GetMapping("/comp/scoutList/{id}")
//    public @ResponseBody List<ResumeRequest> getResumeList(@PathVariable Integer resumeId) {
//        List<ResumeRequest> resumeList = new ArrayList<>();
//        return resumeList;
//    }

    @GetMapping("/comp/mngForm")
    public String mngForm(HttpServletRequest req) {
        // 채용 공고 목록 조회
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Jobopen> temp = compRepository.findJobopenById(sessionUser.getId());
        List<JobopenResponse.DTO> jobopenList = temp.stream().map(jobopen -> new JobopenResponse.DTO(jobopen)).toList();

        jobopenList.forEach(dto -> {
            int count = applyJPARepository.countJobopenApplyById(dto.getId());
            dto.setCount(count);
        });


//        req.setAttribute("count",countApplyList);
        req.setAttribute("jobopenList", jobopenList);
        return "comp/_myPage/mngForm";
    }

    @GetMapping("/comp/profileForm")
    public String profileForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        User compProfile = userJPARepository.findById(sessionUser.getId()).get();
        req.setAttribute("compProfile", compProfile);
        return "comp/_myPage/profileForm";
    }

    @PostMapping("/comp/updateProfile") // 주소 수정 필요!
    public String updateProfile(@RequestParam MultipartFile imgFilename, CompRequest.CompProfileUpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/login";
        }
        // profileDto.setId(sessionUser.getId());
        String img = String.valueOf(imgFilename);
        compService.compUpdateProfile(reqDTO, sessionUser);
        return "redirect:/comp/profileForm";
    }
}