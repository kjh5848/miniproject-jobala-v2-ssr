package com.example.jobala.comp;

import com.example.jobala.apply.ApplyRequest;
import com.example.jobala.apply.ApplyResponse;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import jakarta.servlet.http.HttpServletRequest;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompController {

    private final CompRepository compRepository;


    @GetMapping("/comp/scoutList")
    public String scoutList(HttpServletRequest req){
        List<CompResponse.ScoutListDTO> scoutList = compRepository.scoutList();
        req.setAttribute("scoutList", scoutList);

        return "/comp/scoutList";
    }

    @GetMapping("/comp/scoutList/{id}")
    public String aa(){
        return "null";
    }

    @GetMapping("/comp/mngForm")
    public String mngForm(HttpServletRequest req) {
        List<Jobopen> jobopenList = compRepository.findAll();
        req.setAttribute("jobopenList", jobopenList);
        return "/comp/_myPage/mngForm";
    }

    @GetMapping("/comp/profileForm")
    public String profileForm() {
        return "/comp/_myPage/profileForm";
    }

    @GetMapping("/detail")
    public String getApplicantList(HttpServletRequest request) {
        List<Resume> resumeList = compRepository.findResumeAll();
        request.setAttribute("resumeList", resumeList);

        return "/comp/jobopen/detailForm";
    }

    @GetMapping("/detail/{id}")
    public @ResponseBody List<CompRequest> getresumeList(@PathVariable Integer id) {
        List<CompRequest> resumeList = new ArrayList<>();
        return resumeList;
    }
}
