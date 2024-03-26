package com.example.jobala.apply;


import com.example.jobala._user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class ApplyController {

    private final HttpSession session;
    private final ApplyQueryRepository applyRepository;
    private final ApplyService applyService;
    private final ApplyJPARepository applyJPARepository;

    //기업이 제안한 상태 업데이트
    @PostMapping("comp/applyStatus/update")
    public String updateCompApplicationStatus(
            @RequestParam("applyId") Integer applyId, @RequestParam("status") String status) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        applyService.상태수정(applyId, status);
        return "redirect:/positionForm";
    }

    //게스트가 제안
    @PostMapping("guest/applyStatus/update")
    public String updateGuestApplicationStatus(@RequestParam("applyId") Integer applyId, @RequestParam("status") String status) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        applyService.상태수정(applyId, status);

        return "redirect:/positionForm";
    }

    @PostMapping("/Applys")
    public String apply(ApplyRequest.ApplyRequestDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        System.out.println("지원하기 공고, 이력서 아이디 = " + reqDTO);
        applyService.지원후저장(reqDTO, sessionUser);

        if (sessionUser.getRole() == 1) {
            return "redirect:/guest/resume/" + reqDTO.getResumeId();
        } else {
            return "redirect:/comp/jobopen/" + reqDTO.getJobopenId();
        }
    }

    // 핵심로직 : 지원 정보를 받아와서 상세보기
    @GetMapping("/applys/{applyId}")
    public @ResponseBody List<ApplyRequest> getApplicantProfile(@PathVariable Integer applyId) {
        List<ApplyRequest> applicantProfiles = new ArrayList<>();
        return applicantProfiles;
    }

    @GetMapping("/positionForm")
    public String positionForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        int companyId = sessionUser.getId();

        List<ApplyResponse.ApplyDTO> respDTO = applyRepository.findByUserId(sessionUser.getId(),sessionUser.getRole());
        req.setAttribute("Apply", respDTO);

        ApplyResponse.ApplyStatusDTO respDTO2 = applyService.getCompanyApplyStatus(companyId);
        req.setAttribute("ApplyGuest", respDTO2.getAppliedPositions());

        if (sessionUser.getRole() == 1) {
            return "comp/_myPage/positionForm";
        } else {
            return "guest/_myPage/positionForm";
        }
    }

    // 개인 사용자의 지원 상태 및 받은 제안 페이지
    @GetMapping("/applyForm")
    public String applyForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<ApplyResponse.ApplyDTO> respDTO = applyRepository.findByUserId(sessionUser.getId(),sessionUser.getRole());
        req.setAttribute("Apply", respDTO);


        // 서비스에서 개인 사용자의 지원 상태 및 제안 현황 정보를 가져옴
        var applyStatusFormResponse = applyService.getGuestApplyStatus(sessionUser.getId(),sessionUser.getRole());
        req.setAttribute("ApplyGuest", applyStatusFormResponse.getReceivedOffersReviewing());
        req.setAttribute("ApplyGuest2", applyStatusFormResponse.getReceivedOffersAccepted());
        req.setAttribute("ApplyGuest3", applyStatusFormResponse.getReceivedOffersRejected());

        if (sessionUser.getRole() == 1) {
            return "comp/_myPage/applyForm";
        } else {
            return "guest/_myPage/applyForm";
        }
    }

//// TODO: applyPositionForm, applyStatusForm 삭제 예정

    //    기업이 지원받은 이력서의 상태 여부를 결정
////    @GetMapping("/applyPositionForm")
////    public String applyPositionForm(HttpServletRequest req) {
////        User sessionUser = (User) session.getAttribute("sessionUser");
//
//        List<ApplyResponse.ApplyDTO> responseDTO = applyRepository.findByUserId(sessionUser.getId());
//        req.setAttribute("Apply", responseDTO);
//        // 검토중
//        List<ApplyResponse.ApplyDTO> responseDTO2 = applyRepository.findApplyCompByUserId(sessionUser.getId(), "검토중");
//        req.setAttribute("ApplyComp", responseDTO2);
//        // 합격
////        List<ApplyResponse.ApplyDTO> responseDTO3 = applyRepository.findApplyCompByUserId(sessionUser.getId(), "합격");
////        req.setAttribute("ApplyComp2", responseDTO3);
////        // 불합격
////        List<ApplyResponse.ApplyDTO> responseDTO4 = applyRepository.findApplyCompByUserId(sessionUser.getId(), "불합격");
////        req.setAttribute("ApplyComp3", responseDTO4);
//
//        return "/comp/_myPage/applyPositionForm";
//    }
//
//    @GetMapping("/applyStatusForm")
//    public String applyStatusForm(HttpServletRequest req) {
//        User sessionUser = (User) session.getAttribute("sessionUser");
//
//        int userId = sessionUser.getId();
//        System.out.println(userId);
//        //내가 지원한 공고 현황
//        List<ApplyResponse.ApplyDTO> respDTO = applyRepository.findByUserId(userId);
//        req.setAttribute("Apply", respDTO);
//
//        //기업에 공고 제안 받은거
//        List<ApplyResponse.ApplyDTO2> respDTO2 = applyRepository.findJopOpenByUserId(userId);
//        req.setAttribute("ApplyGuest", respDTO2);
//
//        List<ApplyResponse.ApplyDTO2> respDTO3 = applyRepository.findJopOpenByUserId(userId);
//        req.setAttribute("ApplyGuest2", respDTO3);
//
//        List<ApplyResponse.ApplyDTO2> respDTO4 = applyRepository.findJopOpenByUserId(userId);
//        req.setAttribute("ApplyGuest3", respDTO4);
//
//        return "/guest/_myPage/applyStatusForm";
//    }
// 기업 사용자의 공고 관리 및 지원자 상태 페이지


}