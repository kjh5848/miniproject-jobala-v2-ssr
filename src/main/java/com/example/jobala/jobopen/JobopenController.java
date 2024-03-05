package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import jakarta.servlet.http.HttpServletRequest;
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
    public String jobopenSave(JobopenRequest.JobopenSaveDTO reqDTO) {
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
        req.setAttribute("jobopen", jobopen);


        return "/comp/jobopen/detailForm";
    }


}
