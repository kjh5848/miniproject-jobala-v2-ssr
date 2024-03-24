package com.example.jobala.resume;

import com.example.jobala.Pic.Pic;
import com.example.jobala.Pic.PicQueryRepository;
import com.example.jobala._user.User;
import com.example.jobala._user.UserQueryRepository;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenQueryRepository;
import com.example.jobala.scrap.Scrap;
import com.example.jobala.scrap.ScrapQueryRepository;
import com.example.jobala.skill.Skill;
import com.example.jobala.skill.SkillQueryRepository;
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
public class ResumeController {

    private final SkillQueryRepository skillRepository;
    private final ResumeQueryRepository resumeRepository;
    private final HttpSession session;
    private final UserQueryRepository userRepository;
    private final PicQueryRepository picRepository;
    private final ScrapQueryRepository scrapRepository;
    private final JobopenQueryRepository jobopenRepository;
    private final ResumeService resumeService;
    private final ResumeJPARepository resumeJPARepository;

    //TODO: saveForm 삭제예정
    @GetMapping("/guest/resume/saveForm")
    public String saveForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        req.setAttribute("user", sessionUser);
        return "/guest/resume/saveForm";
    }

    //이력서 업데이트
    @PostMapping("/guest/resume/{id}/update")
    public String update(@PathVariable Integer id, ResumeRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.이력서수정(id, reqDTO,sessionUser.getId());
        System.out.println("이력서 수정 실행");
        return "redirect:/guest/mngForm";
    }

    // TODO: 글조회로 변경예정
    @GetMapping("/guest/resume/{id}/updateForm")
    public String updateForm(@PathVariable Integer id, HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Resume resume = resumeService.이력서보기(id);

        req.setAttribute("user", sessionUser);
        req.setAttribute("resume", resume);
        return "/guest/resume/updateForm";
    }

    //이력서 상세보기
    @GetMapping("/guest/resume/{id}")
    public String detailForm(@PathVariable Integer id, HttpServletRequest req) {
//        기업에서 이력서를 볼때 보이는 최근 이력서 4개 조인하는


        Resume resume = resumeService.이력서보기(id);
        // 이력서 상세보기에 이미지 불러오기
//        Pic pic = picRepository.resumeFindByPic(id);
//        req.setAttribute("pic", pic);


        boolean isGuestScrap = false;
        User sessionUser = null;
        // 스크랩
        try {
            sessionUser = (User) session.getAttribute("sessionUser");
            if (sessionUser.getRole() == 1) {
                isGuestScrap = true;
                req.setAttribute("isGuestScrap", isGuestScrap);
                Scrap scrap = scrapRepository.findCompScrapById(id, sessionUser.getId());
                req.setAttribute("scrap", scrap);
            }
        } catch (Exception e) {
        }


        int userId = resume.getUser().getId();
//        User user = (User) session.getAttribute("sessionUser"); 세션에서 가져오면 자기 밖에 정보를 못본다
        User user = userRepository.findById(userId);


        req.setAttribute("user", user);
        req.setAttribute("resume", resume);
        return "/guest/resume/detailForm";
    }

    //이력서 등록
    @PostMapping("/guest/resume/save")
    public String save(ResumeRequest.SaveDTO resumeSaveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.이력서등록(resumeSaveDTO, sessionUser);
        return "redirect:/guest/mngForm";
    }

    //이력서 삭제
    @PostMapping("/resume/{id}/delete")
    public String delete(@PathVariable int id, ResumeRequest.DeleteDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.이력서삭제(id, reqDTO.getId());
        return "redirect:/guest/mngForm";
    }
}