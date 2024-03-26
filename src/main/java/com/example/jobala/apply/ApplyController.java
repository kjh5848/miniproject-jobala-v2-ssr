package com.example.jobala.apply;

import com.example.jobala._user.*;
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
    private final ApplyJPARepository applyJPARepository;
    private final ApplyQueryRepository applyQueryRepository;

    //기업이 제안한 상태 업데이트
    @PostMapping("comp/applyStatus/update")
    public String updateCompApplicationStatus(
            @RequestParam("applyId") Integer applyId, @RequestParam("status") String status) {
        applyService.상태수정(applyId, status);
        return "redirect:/applyForm";
    }

    //게스트가 제안
    @PostMapping("guest/applyStatus/update")
    public String updateGuestApplicationStatus(@RequestParam("applyId") Integer applyId, @RequestParam("status") String status) {
        applyService.상태수정(applyId, status);

        return "redirect:/positionForm";
    }

    //개인이 이력서 지원하기
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

        List<ApplyResponse.ApplyDTO2> respDTO2 = applyQueryRepository.findJopOpenByUserId(sessionUser.getId());
        req.setAttribute("ApplyGuest", respDTO2);

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

        List<ApplyResponse.ApplyDTO> respDTO = applyQueryRepository.findByUserId(sessionUser.getId(),sessionUser.getRole());
        req.setAttribute("Apply", respDTO);

        if (sessionUser.getRole() == 1) {
            return "comp/_myPage/applyForm";
        } else {
            return "guest/_myPage/applyForm";
        }
    }


}