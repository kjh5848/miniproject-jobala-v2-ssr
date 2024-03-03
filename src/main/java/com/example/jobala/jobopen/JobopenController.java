package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JobopenController {

    private final JobopenRepository jobopenRepository;
    private final HttpSession session;

    @PostMapping("/comp/jobopen/write")
    public String jobopenWrite(JobopenRequest.WriteDTO writeDTO, JobopenRequest.SkillDTO skillDTO) {
        System.out.println("reqDTO = " + writeDTO);

        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println("sessionUser.getId() = " + sessionUser.getId());
        jobopenRepository.skillSave(skillDTO, sessionUser.getId(), sessionUser.getRole());
        jobopenRepository.save(writeDTO, sessionUser.getId(), sessionUser.getRole());


        return "redirect:/comp/mngForm";
    }


    @GetMapping("/comp/writeForm")
    public String writeForm() {
        return "/comp/jobOpen/writeForm";
    }

    @GetMapping("/comp/{id}")
    public String detailForm(@PathVariable int id) {
        System.out.println("id = " + id);

        Jobopen jobopen = jobopenRepository.findByIdWithUser(id);

        return "/comp/jobopen/detailForm";
    }

    @GetMapping("/comp/updateForm")
    public String updateForm() {
        return "/comp/jobopen/updateForm";
    }

}
