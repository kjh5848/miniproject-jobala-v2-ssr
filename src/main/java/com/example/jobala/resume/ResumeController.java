package com.example.jobala.resume;

import com.example.jobala.Pic.Pic;
import com.example.jobala.Pic.PicRepository;
import com.example.jobala.Pic.PicRequest;
import com.example.jobala._user.User;
import com.example.jobala._user.UserRepository;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenRepository;
import com.example.jobala.scrap.Scrap;
import com.example.jobala.scrap.ScrapRepository;
import com.example.jobala.skill.Skill;
import com.example.jobala.skill.SkillRepository;
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

    private final SkillRepository skillRepository;
    private final ResumeRepository resumeRepository;
    private final HttpSession session;
    private final UserRepository userRepository;
    private final PicRepository picRepository;
    private final ScrapRepository scrapRepository;
    private final JobopenRepository jobopenRepository;

    @GetMapping("/guest/resume/saveForm")
    public String saveForm(HttpServletRequest req) {
        User user = (User) session.getAttribute("sessionUser");
        req.setAttribute("user", user);
        return "/guest/resume/saveForm";
    }

    @PostMapping("/guest/resume/{id}/update")
    public String update(@PathVariable Integer id, ResumeRequest.UpdateDTO reqDTO) {
        Resume resume = resumeRepository.findById(id);
        System.out.println("id = " + id);
        System.out.println("resume.getId() = " + resume.getId());
        resumeRepository.update(resume.getId(), reqDTO);
        return "redirect:/guest/mngForm";
    }

    @GetMapping("/guest/resume/{id}/updateForm")
    public String updateForm(@PathVariable Integer id, HttpServletRequest req) {
        Resume resume = resumeRepository.findById(id);
        User user = (User) session.getAttribute("sessionUser");

        // 이력서에 저장된 이미지 파일 정보 가져오기
        Pic pic = picRepository.resumeFindByPic(id);

        req.setAttribute("pic", pic); // 이미지 파일 경로를 request에 저장

        req.setAttribute("user", user);
        req.setAttribute("resume", resume);

        return "/guest/resume/updateForm";
    }


    @GetMapping("/guest/resume/{id}")
    public String detailForm(@PathVariable Integer id, HttpServletRequest req) {
        Resume resume = resumeRepository.findById(id);

        // 이력서 상세보기에 이미지 불러오기
        Pic pic = picRepository.resumeFindByPic(id);
        req.setAttribute("pic", pic);
        
        User sessionUser = null;
        // 스크랩
        try {
            sessionUser = (User) session.getAttribute("sessionUser");
            Scrap scrap = scrapRepository.findCompScrapById(id, sessionUser.getId());
            req.setAttribute("scrap", scrap);
        } catch (Exception e) {
        }

        if (sessionUser != null) {
            List<Jobopen> jobopenList = jobopenRepository.findJobopenById(sessionUser);
            req.setAttribute("jobopenList", jobopenList);
        }


        int userId = resume.getUserId();
//        User user = (User) session.getAttribute("sessionUser"); 세션에서 가져오면 자기 밖에 정보를 못본다
        User user = userRepository.findById(userId);

        // name은 JSON 이기 때문에 List 로 바꿔서 뿌려야 함.
        Skill skills = skillRepository.findByResumeId(id);
        String json = skills.getName();
        // JSON -> List
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> skillsList = gson.fromJson(json, type);
        System.out.println("다시 바꾼 결과 = " + skillsList);
        req.setAttribute("skillsList", skillsList);

        req.setAttribute("user", user);
        req.setAttribute("resume", resume);
        return "/guest/resume/detailForm";
    }

    @PostMapping("/guest/resume/save")
    public String save(ResumeRequest.SaveDTO resumeSaveDTO) {
        User user = (User) session.getAttribute("sessionUser");
        System.out.println("리스트 = " + resumeSaveDTO.getSkills());
        resumeRepository.save(resumeSaveDTO, user);
        return "redirect:/guest/mngForm";
    }

    @PostMapping("/resume/{id}/delete")
    public String delete(ResumeRequest.DeleteDTO deleteDTO) {
        resumeRepository.delete(deleteDTO.getId());
        return "redirect:/guest/mngForm";
    }
}
