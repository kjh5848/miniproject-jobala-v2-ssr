package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala.guest.GuestQueryRepository;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeQueryRepository;
import com.example.jobala.scrap.Scrap;
import com.example.jobala.scrap.ScrapQueryRepository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Type;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class JobopenController {

    private final JobopenQueryRepository jobopenRepository;
    private final GuestQueryRepository guestRepository;
    private final ScrapQueryRepository scrapRepository;
    private final HttpSession session;
    private final JobopenService jobopenService;
    private final JobopenJPARepository jobopenJPARepository;

    //공고 삭제
    @PostMapping("/comp/jobopen/{id}/detete")
    public String delete(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        jobopenService.공고삭제(id,sessionUser.getId());
        return "redirect:/comp/mngForm";
    }

    //공고 수정
    @PostMapping("/comp/jobopen/{id}/update")
    public String update(@PathVariable Integer id, JobopenRequest.UpdateDTO reqDTO) {
        System.out.println("id = " + id);
        User sessionUser = (User) session.getAttribute("sessionUser");
        jobopenService.공고수정하기(id,sessionUser.getId(),reqDTO);

        return "redirect:/comp/mngForm";
    }

    //TODO: 글조회로 변경예정
    @GetMapping("/comp/jobopen/{id}/updateForm")
    public String updateForm(@PathVariable Integer id, HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        Jobopen jobopen = jobopenService.공고보기(id);
        req.setAttribute("jobopen", jobopen);
        return "/comp/jobopen/updateForm";
    }

    //공고 등록
    @PostMapping("/comp/jobopen/save")
    public String jobopenSave(JobopenRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        jobopenService.공고등록(reqDTO, sessionUser);
        return "redirect:/comp/mngForm";
    }

    // TODO : 삭제 예정
    @GetMapping("/comp/jobopen/saveForm")
    public String saveForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        return "/comp/jobopen/saveForm";
    }

    //공고 보기
    @GetMapping("/comp/jobopen/{id}")
    public String detailForm(@PathVariable int id, HttpServletRequest req) {
        boolean isCompScrap = false;
        User user = null;
        try {
            user = (User) session.getAttribute("sessionUser");
            if (user.getRole() == 0) {
                isCompScrap = true;
                req.setAttribute("isCompScrap", isCompScrap);
                Scrap scrap = scrapRepository.findGuestScrapById(id, user.getId());
                req.setAttribute("scrap", scrap);
            }
        } catch (Exception e) {
        }
        req.setAttribute("user", user);

        // resumeList 메소드를 호출할 때 사용자 ID를 매개변수로 전달
        if (user != null) {
            List<Resume> resumeList2 = jobopenRepository.findResumeById(user);
            req.setAttribute("resumeList2", resumeList2);
        }

        Jobopen jobopen = jobopenService.공고보기(id);
        JobopenResponse.JobopenDetailDTO JobopenRespDTO = jobopenRepository.findByUserAndJobopen(id);

        req.setAttribute("jobopen", jobopen);
        req.setAttribute("JobopenRespDTO", JobopenRespDTO);

        return "/comp/jobopen/detailForm";

    }
}