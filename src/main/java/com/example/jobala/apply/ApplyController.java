package com.example.jobala.apply;


import com.example.jobala._user.User;
import com.example.jobala.board.Board;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
//@RequestMapping("/applys")
@RequiredArgsConstructor
public class ApplyController {

    private final HttpSession session;
    private final ApplyRepository applyRepository;

//    @PostMapping("/updateApplicationStatus")
//    public ResponseEntity<String> updateApplicationStatus(
//            @RequestParam("applyId") Integer applicationId,
//            @RequestParam("status") Integer status) {
//
//        // 상태값을 문자열로 변환
//        String statusString = convertStatusToString(status);
//
//        // 상태 업데이트 시도
//        try {
//            applyRepository.passFailStatus(applicationId, statusString);
//            return ResponseEntity.ok("지원 상태가 성공적으로 업데이트되었습니다.");
//        } catch (Exception e) {
//            // 예외 처리 및 에러 응답
//            return ResponseEntity.badRequest().body("상태 업데이트를 실패하였습니다.");
//        }
//    }
//
//    private String convertStatusToString(Integer status) {
//        if (status.equals(0)) {
//            return "합격";
//        } else if (status.equals(1)) {
//            return "불합격";
//        } else {
//            return "미정"; // 기본값
//        }
//    }


    @PostMapping("/resumeApplys")
    public String apply(ApplyResponse.ResumeApplyDTO respDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        System.out.println("지원하기 이력서, 공고 아이디 = " + respDTO);
        applyRepository.resumeApplySave(respDTO,sessionUser);

        return "redirect:/comp/jobopen/" + respDTO.getJobopenId();
    }


//    @GetMapping("/applyPositionForm")
//    public String applyPositionForm() {
//
//        return "/comp/_myPage/applyPositionForm";
//
//    }

    // 핵심로직 : 지원 목록 가져오기
//    @GetMapping("/applys")
//    public String getApplicantList(HttpServletRequest request) {
//        // 1. 로그인한 아이디 가져오기 (세션) session.getAttribute();
//        int sessionUserId = 3;
//
//        // 2. 조회
//        List<ApplyResponse.ApplyDTO> responseDTO = applyRepository.findAllByUserId(sessionUserId);
//        request.setAttribute("applys", responseDTO);
//        List<ApplyResponse.ApplyDTO> responseDTO2 = applyRepository.findAllByUserId(sessionUserId);
//        request.setAttribute("position", responseDTO2);
//
////        // 페이징
////        int pageIndex = page - 1;
////
////        List<Apply> applyList = applyRepository.findAll(pageIndex);
////
////        // 전체 페이지 개수 계산
////        int totalCount = applyRepository.count().intValue();
////        int pageSize = 3; // 한 페이지에 표시할 아이템의 수
////        int totalPageCount = (totalCount + pageSize - 1) / pageSize;
////
////        request.setAttribute("applyList", applyList);
////        request.setAttribute("first", page == 1);
////        request.setAttribute("last", page.equals(totalPageCount));
////        request.setAttribute("prev", page > 1 ? page - 1 : 1);
////        request.setAttribute("next", page < totalPageCount ? page + 1 : totalPageCount);
//
//
//        return "comp/_myPage/applyPositionForm";
//    }


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

// 페이징


    @GetMapping("/applyPositionForm")
    public String applyPositionForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ApplyResponse.ApplyDTO> responseDTO = applyRepository.findCompApplyByUserId(sessionUser.getId());
        req.setAttribute("compApply", responseDTO);
        List<ApplyResponse.ApplyDTO> responseDTO2 = applyRepository.findGuestApplyByUserId(sessionUser.getId());
        req.setAttribute("guestApply", responseDTO2);

        return "/comp/_myPage/applyPositionForm";
    }

}