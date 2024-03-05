package com.example.jobala.apply;



import com.example.jobala.resume.ResumeRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/applys")
@RequiredArgsConstructor
public class ApplyController {



    @GetMapping("/applyPositionForm/id")
    public String applyStatusForm() {
        return "/guest/_myPage/applyStatusForm";
    }

    @GetMapping("/applyPositionForm")
    public String applyPositionForm() {
        return "/comp/_myPage/applyPositionForm";

    }
}
