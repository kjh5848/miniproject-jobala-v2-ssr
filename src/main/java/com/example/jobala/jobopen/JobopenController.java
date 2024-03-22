package com.example.jobala.jobopen;

import com.example.jobala.Pic.Pic;
import com.example.jobala.Pic.PicQueryRepository;
import com.example.jobala._user.User;
import com.example.jobala.guest.GuestQueryRepository;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeQueryRepository;
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
public class JobopenController {

    private final JobopenQueryRepository jobopenRepository;
    private final SkillQueryRepository skillRepository;
    private final GuestQueryRepository guestRepository;
    private final ScrapQueryRepository scrapRepository;
    private final PicQueryRepository picRepository;
    private final ResumeQueryRepository resumeRepository;
    private final HttpSession session;
    private final JobopenService jobopenService;
    private Pic pic;

    @PostMapping("/comp/jobopen/{id}/detete")
    public String delete(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        jobopenService.공고삭제(id,sessionUser.getId());
        return "redirect:/comp/mngForm";
    }

    @PostMapping("/comp/jobopen/{id}/update")
    public String update(@PathVariable Integer id, JobopenRequest.UpdateDTO reqDTO) {
        System.out.println("id = " + id);
        User sessionUser = (User) session.getAttribute("sessionUser");
        jobopenService.공고수정하기(id,sessionUser.getId(),reqDTO);

        return "redirect:/comp/mngForm";
    }

    @GetMapping("/comp/jobopen/{id}/updateForm")
    public String updateForm(@PathVariable Integer id, HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        Jobopen jobopen = jobopenRepository.findById(id);
        req.setAttribute("jobopen", jobopen);

        // 이력서에 저장된 이미지 파일 정보 가져오기
        Pic pic = picRepository.jobopenFindByPic(id);
        System.out.println(pic);
        req.setAttribute("pic", pic); // 이미지 파일 경로를 request에 저장

        return "/comp/jobopen/updateForm";
    }

    @PostMapping("/comp/jobopen/save")
    public String jobopenSave(JobopenRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println("응애 : "+reqDTO);
        jobopenService.공고등록(reqDTO, sessionUser);
        return "redirect:/comp/mngForm";
    }

    @GetMapping("/comp/jobopen/saveForm")
    public String saveForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        return "/comp/jobopen/saveForm";
    }

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
        // jobopenDetail 수정 부분
        Jobopen jobopen = jobopenService.jobopenDetail(id);
        JobopenResponse.JobopenDetailDTO JobopenRespDTO = jobopenRepository.findByUserAndJobopen(id);


        // name은 JSON 이기 때문에 List 로 바꿔서 뿌려야 함.
        Skill skills = skillRepository.findByJobopenId(id);
        String json = skills.getName();

        // JSON -> List
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> skillsList = gson.fromJson(json, type);
        System.out.println("다시 바꾼 결과 = " + skillsList);
        req.setAttribute("skillsList", skillsList);
        req.setAttribute("jobopen", jobopen);
        req.setAttribute("JobopenRespDTO", JobopenRespDTO);

        // 이력서 상세보기에 이미지 불러오기
        Pic pic = picRepository.jobopenFindByPic(id);
        req.setAttribute("pic", pic);

        return "/comp/jobopen/detailForm";

    }
}