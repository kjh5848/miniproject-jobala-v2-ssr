package com.example.jobala.resume;

import com.example.jobala._user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeRepository resumeRepository;
    private final HttpSession session;

    @GetMapping("/guest/resume/writeForm")
    public String writeForm(HttpServletRequest request) {
        User user = (User) session.getAttribute("sessionUser");
        request.setAttribute("user", user);
        return "/guest/resume/writeForm";
    }

    @PostMapping("/guest/resume/{id}/update")
    public String update(@PathVariable Integer id, ResumeRequest.UpdateDTO requestDTO) {
        Resume resume = resumeRepository.findById(id);
        System.out.println("id = " + id);
        System.out.println("resume.getId() = " + resume.getId());
        resumeRepository.update(resume.getId(), requestDTO);
        return "redirect:/guest/mngForm";
    }

    @GetMapping("/guest/resume/{id}/updateForm")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        Resume resume = resumeRepository.findById(id);
        User user = (User) session.getAttribute("sessionUser");
        request.setAttribute("user", user);
        request.setAttribute("resume", resume);
        return "/guest/resume/updateForm";
    }

    @GetMapping("/guest/resume/{id}")
    public String detailForm(@PathVariable Integer id, HttpServletRequest request) {
        Resume resume = resumeRepository.findById(id);
        User user = (User) session.getAttribute("sessionUser");
        System.out.println("sessionUser = " + user);

        request.setAttribute("user", user);
        request.setAttribute("resume", resume);
        return "/guest/resume/detailForm";
    }

    @PostMapping("/guest/resume/write")
    public String write(ResumeRequest.SaveDTO resumeSaveDTO) {
        User user = (User) session.getAttribute("sessionUser");
        resumeRepository.save(resumeSaveDTO, user);
        return "redirect:/guest/mngForm";
    }

    @PostMapping("/resume/{id}/delete")
    public String delete(ResumeRequest.DeleteDTO deleteDTO) {
        resumeRepository.delete(deleteDTO.getId());
        return "redirect:/guest/mngForm";
    }


}
