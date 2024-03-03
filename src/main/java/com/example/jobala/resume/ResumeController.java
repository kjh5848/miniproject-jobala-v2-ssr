package com.example.jobala.resume;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeRepository resumeRepository;

    @GetMapping("/guest/resume/writeForm")
    public String writeForm() {
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
        request.setAttribute("resume", resume);

        return "/guest/resume/updateForm";
    }

    @GetMapping("/guest/resume/{id}")
    public String detailForm(@PathVariable Integer id, HttpServletRequest request) {
        Resume resume = resumeRepository.findById(id);
        request.setAttribute("resume", resume);
        return "/guest/resume/detailForm";
    }

    @PostMapping("/guest/resume/write")
    public String write(ResumeRequest.SaveDTO resumeSaveDTO) {
        int userId = 1;
        resumeRepository.save(resumeSaveDTO, userId);
        return "redirect:/guest/mngForm";
    }

    @PostMapping("/resume/{id}/delete")
    public String delete(ResumeRequest.DeleteDTO deleteDTO) {
        resumeRepository.delete(deleteDTO.getId());
        return "redirect:/guest/mngForm";
    }


}
