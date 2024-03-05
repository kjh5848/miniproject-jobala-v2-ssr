package com.example.jobala.comp;

import com.example.jobala.jobopen.Jobopen;
import jakarta.servlet.http.HttpServletRequest;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompController {

    private final CompRepository compRepository;


    @GetMapping("/comp/scoutList")
    public String scoutList(HttpServletRequest req){
        List<CompResponse.ScoutListDTO> scoutList = compRepository.scoutList();
        req.setAttribute("scoutList", scoutList);

        return "/comp/scoutList";
    }


    @GetMapping("/comp/mngForm")
    public String mngForm(HttpServletRequest req) {
        List<Jobopen> jobopenList = compRepository.findAll();
        req.setAttribute("jobopenList", jobopenList);
        return "/comp/_myPage/mngForm";
    }

    @GetMapping("/comp/profileForm")
    public String profileForm() {
        return "/comp/_myPage/profileForm";
    }


}
