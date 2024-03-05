package com.example.jobala.apply;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/applys")
@RequiredArgsConstructor
public class ApplyController {

    private final HttpSession session;
    private final ApplyRepository applyRepository;

    @GetMapping("/applys/applyPositionForm/{jobopenId}")
    public String applyStatusForm(@PathVariable Integer jobopenId) {
        return "/guest/_myPage/applyStatusForm";
    }

    @GetMapping("/applys/applyPositionForm")
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

        return "comp/_myPage/applyPositionForm";
    }

    // 핵심로직 : 지원 목록 가져오기
    @GetMapping("/applys/v2")
    public @ResponseBody List<ApplyResponse.ApplyDTO> getApplicantListV2() {
        // 1. 로그인한 아이디 가져오기 (세션) session.getAttribute();
        int sessionUserId = 3;

        // 2. 조회
        List<ApplyResponse.ApplyDTO> responseDTO = applyRepository.findAllByUserId(sessionUserId);

        return responseDTO;
    }

    // 핵심로직 : 지원 정보를 받아와서 상세보기
    @GetMapping("/applys/{applyId}")
    public @ResponseBody List<ApplyRequest> getApplicantProfile(@PathVariable Integer applyId) {
        List<ApplyRequest> applicantProfiles = new ArrayList<>();

        return applicantProfiles;
    }


//@Controller
//@RequiredArgsConstructor
//public class ApplyController {
//
//    private final HttpSession session;
//
//    private final ApplyRepository applyRepository;
//    private final ResumeRepository resumeRepository;
//    private final JobopenRepository jobopenRepository;


//    @GetMapping("/applyPositionForm/{jobopenId}")
//    public ResponseEntity<List<ApplyResponse.CardDetailDTO>> getApplicantsWithResumesByJobOpenId(@PathVariable Integer jobopenId) {
//        List<Apply> applies = applyRepository.findByJobOpenId(jobopenId);
//        List<ApplyResponse.CardDetailDTO> cardDetails = new ArrayList<>();
//
//        // Jobopen 객체를 가져옴
//        Jobopen jobopen = jobopenRepository.findById(jobopenId);
//        if (jobopen == null) {
//            return ResponseEntity.notFound().build(); // 해당 jobopen이 없는 경우 404 에러 반환
//        }
//
//        for (Apply apply : applies) {
//            Resume resume = resumeRepository.findByResumeId(apply.getResumeId());
//            if (resume != null) {
//                ApplyResponse.CardDetailDTO cardDetail = new ApplyResponse.CardDetailDTO();
//                cardDetail.setName(resume.getName());
//                cardDetail.setJobopenTitle(jobopen.getJobopenTitle());
//                cardDetail.setResumeTitle(resume.getResumeTitle());
//                cardDetail.setEdu(resume.getEdu());
//                cardDetail.setState(apply.getState());
//                cardDetail.setCreatedAt(apply.getCreatedAt());
//                cardDetails.add(cardDetail);
//            }
//        }
//
//        return ResponseEntity.ok(cardDetails);
//    }


}
