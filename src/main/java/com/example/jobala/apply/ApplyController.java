package com.example.jobala.apply;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
//@RequestMapping("/applys")
@RequiredArgsConstructor
public class ApplyController {

    private final ApplyRepository applyRepository;


    @PostMapping("/applys/{id}")
    public String setApplicantList() {
        return null;
    }


    @GetMapping("/applyPositionForm")
    public String applyPositionForm() {

        return "/comp/_myPage/applyPositionForm";

    }

    // 핵심로직 : 지원 목록 가져오기
    @GetMapping("/applys")
    public String getApplicantList(HttpServletRequest request) {
        // 1. 로그인한 아이디 가져오기 (세션) session.getAttribute();
        int sessionUserId = 3;

        // 2. 조회
        List<ApplyResponse.ApplyDTO> responseDTO = applyRepository.findAllByUserId(sessionUserId);
        request.setAttribute("applys", responseDTO);
        List<ApplyResponse.ApplyDTO> responseDTO2 = applyRepository.findAllByUserId(sessionUserId);
        request.setAttribute("position", responseDTO2);


        return "comp/_myPage/applyPositionForm";
    }


//    // 핵심로직 : 지원 목록 가져오기
//    @GetMapping("/applys/v2")
//    public @ResponseBody List<ApplyResponse.ApplyDTO> getApplicantListV2() {
//        // 1. 로그인한 아이디 가져오기 (세션) session.getAttribute();
//        int sessionUserId = 3;
//
//        // 2. 조회
//        List<ApplyResponse.ApplyDTO> responseDTO = applyRepository.findAllByUserId(sessionUserId);
//
//        return responseDTO;
//    }


    // 핵심로직 : 지원 정보를 받아와서 상세보기
    @GetMapping("/applys/{applyId}")
    public @ResponseBody List<ApplyRequest> getApplicantProfile(@PathVariable Integer applyId) {
        List<ApplyRequest> applicantProfiles = new ArrayList<>();
        return applicantProfiles;
    }

}