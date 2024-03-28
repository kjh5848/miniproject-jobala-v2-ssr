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
    private final ApplyService applyService;
    private final ApplyQueryRepository applyQueryRepository;

    //기업 - 지원받은 이력서 합격, 불합격 상태 업데이트
    @PostMapping("comp/applyStatus/update")  // 주소 수정 필요
    public String updateCompApplicationStatus(
            @RequestParam("applyId") Integer applyId, @RequestParam("status") String status) {
        applyService.statusUpdate(applyId, status);
        return "redirect:/applyForm";
    }

    //개인 - 제안받은 공고 수락, 거절 상태 업데이트
    @PostMapping("guest/applyStatus/update")  // 주소 수정 필요
    public String updateGuestApplicationStatus(@RequestParam("applyId") Integer applyId, @RequestParam("status") String status) {
        applyService.statusUpdate(applyId, status);
        return "redirect:/positionForm";
    }

    //개인 - 이력서 지원하기
    @PostMapping("/Applys") // 주소 수정 필요
    public String apply(ApplyRequest.ApplyRequestDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        applyService.saveAfterApply(reqDTO, sessionUser);
        if (sessionUser.getRole() == 1) {
            return "redirect:/guest/resume/" + reqDTO.getResumeId();
        } else {
            return "redirect:/comp/jobopen/" + reqDTO.getJobopenId();
        }
    }

    //기업 - 포지션 제안한 현황보기
    //개인 - 포지션 제안 받은 현황보기
    @GetMapping("/positionForm")
    public String positionForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser.getRole() == 1) {
            List<ApplyResponse.CompPositionDTO> respDTO = applyService.findApplyCompByUserId(sessionUser.getId());
            req.setAttribute("CompPosition", respDTO);
            return "comp/_myPage/positionForm";
        } else {
            List<ApplyResponse.GuestPositionDTO> respDTO = applyQueryRepository.findJopOpenByUserId(sessionUser.getId());
            req.setAttribute("GuestPosition", respDTO);
            return "guest/_myPage/positionForm";
        }
    }

    //기업 - 이력서 지원받은 현황보기
    //개인 - 이력서 지원 현황보기
    @GetMapping("/applyForm")
    public String applyForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser.getRole() == 1) {
            List<ApplyResponse.CompApplyDTO> respDTO = applyQueryRepository.findByUserId(sessionUser.getId(),sessionUser.getRole());
            req.setAttribute("CompApply", respDTO);
            return "comp/_myPage/applyForm";
        } else {
            List<ApplyResponse.GuestApplyDTO> respDTO = applyService.findApplyGuestByUserId(sessionUser.getId());
//            List<ApplyResponse.GuestApplyDTO> respDTO = applyQueryRepository.findApplyCompByUserId(sessionUser.getId());
            req.setAttribute("GuestApply", respDTO);
            return "guest/_myPage/applyForm";
        }
    }


}