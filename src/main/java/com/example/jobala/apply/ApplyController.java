package com.example.jobala.apply;



import com.example.jobala.resume.ResumeRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/applys")
@RequiredArgsConstructor
public class ApplyController {

    private final ApplicantService applicantService;


    @GetMapping("/{applyId}")
    public ResponseEntity<List<ApplyRequest>> getApplicantProfile(@PathVariable Integer applyId) {
        List<ApplyRequest> applicantProfiles = applicantService.getApplicantProfile(applyId);
        return ResponseEntity.ok().body(applicantProfiles);
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

    @GetMapping("/applyPositionForm/{jobopenId}")
    public String applyStatusForm(@PathVariable Integer jobopenId) {
        return "/guest/_myPage/applyStatusForm";
    }

    @GetMapping("/applyPositionForm")
    public String applyPositionForm() {
        return "/comp/_myPage/applyPositionForm";

    }
}
