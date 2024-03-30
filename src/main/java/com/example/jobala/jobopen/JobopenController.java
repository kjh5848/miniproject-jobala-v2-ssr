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
    @PostMapping("/comp/jobopen/{id}/detete")  // 주소 수정 필요
    public String delete(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        jobopenService.jobopenDelete(id,sessionUser.getId());
        return "redirect:/comp/mngForm";
    }

    //공고 수정
    @PostMapping("/comp/jobopen/{id}/update")  // 주소 수정 필요
    public String update(@PathVariable Integer id, JobopenRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        jobopenService.jobopenUpdate(id,sessionUser.getId(),reqDTO);

        return "redirect:/comp/mngForm";
    }

    //TODO: 글조회로 변경예정
    @GetMapping("/comp/jobopen/{id}/updateForm")
    public String updateForm(@PathVariable Integer id, HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        JobopenResponse.DetailDTO respDTO = jobopenService.findJobopenById(id, sessionUser);
        req.setAttribute("jobopen", respDTO);

        // 업데이트 페이지에서 체크박스 체크 로직
        JobopenResponse.CheckBoxDTO checkedSkillsList = jobopenService.getCheckedSkills(id);
        req.setAttribute("checkedSkillsList", checkedSkillsList);
        return "comp/jobopen/updateForm";
    }

    //공고 등록
    @PostMapping("/comp/jobopen/save")  // 주소 수정 필요
    public String jobopenSave(JobopenRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        jobopenService.jobopenSave(reqDTO, sessionUser);
        return "redirect:/comp/mngForm";
    }

    // TODO : saveForm 삭제 예정
    @GetMapping("/comp/jobopen/saveForm")
    public String saveForm() {
        return "comp/jobopen/saveForm";
    }

    //공고 보기
    @GetMapping("/comp/jobopen/{id}")
    public String detailForm(@PathVariable int id, HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 채용공고 정보 가져오기
        JobopenResponse.DetailDTO respDTO = jobopenService.findJobopenById(id, sessionUser);
        req.setAttribute("jobopen", respDTO);

        return "comp/jobopen/detailForm";
    }

}