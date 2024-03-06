package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala.guest.GuestRepository;
import com.example.jobala.resume.Resume;
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
public class JobopenController {

    private final JobopenRepository jobopenRepository;
    private final SkillRepository skillRepository;
    private final GuestRepository guestRepository;

    private final HttpSession session;

    @PostMapping("/comp/jobopen/{id}/detete")
    public String delete(@PathVariable int id) {
        jobopenRepository.delete(id);
        return "redirect:/comp/mngForm";
    }

    @PostMapping("/comp/jobopen/{id}/update")
    public String update(@PathVariable Integer id, JobopenRequest.UpdateDTO reqDTO) {
        Jobopen jobopen = jobopenRepository.findById(id);
        jobopenRepository.update(jobopen, reqDTO);
        return "redirect:/comp/mngForm";
    }

    @GetMapping("/comp/jobopen/{id}/updateForm")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        Jobopen jobopen = jobopenRepository.findById(id);
        request.setAttribute("jobopen", jobopen);

        return "/comp/jobopen/updateForm";
    }

    @PostMapping("/comp/jobopen/save")
    public String jobopenSave(JobopenRequest.SaveDTO reqDTO) {
        System.out.println("reqDTO = " + reqDTO);
        User sessionUser = (User) session.getAttribute("sessionUser");
        jobopenRepository.save(reqDTO, sessionUser);
        return "redirect:/comp/mngForm";
    }

    @GetMapping("/comp/jobopen/saveForm")
    public String saveForm(HttpServletRequest req) {
        User user = (User) session.getAttribute("sessionUser");
        req.setAttribute("user", user);
        return "/comp/jobOpen/saveForm";
    }

    @GetMapping("/comp/jobopen/{id}")
    public String detailForm(@PathVariable int id, HttpServletRequest req) {

        Jobopen jobopen = jobopenRepository.findByIdWithUser(id);

        // name은 JSON 이기 때문에 List 로 바꿔서 뿌려야 함.
        Skill skills = skillRepository.findByJobopenId(id);
        String json = skills.getName();
        // JSON -> List
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> skillsList = gson.fromJson(json, type);
        System.out.println("다시 바꾼 결과 = " + skillsList);
        req.setAttribute("skillsList", skillsList);

        //이력서 리스트 불러오기
        List<Resume> resumeList = jobopenRepository.findByResumeAll();

        req.setAttribute("jobopen", jobopen);
        req.setAttribute("resumeList", resumeList);


        return "/comp/jobopen/detailForm";
    }


}
