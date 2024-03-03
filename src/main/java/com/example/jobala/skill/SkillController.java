package com.example.jobala.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SkillController {
    private final SkillRepository skillRepository;

    @PostMapping("/comp/skill/")
    public String compSkill(SkillRequest.CompSkillDTO reqDTO) {

        skillRepository.save(reqDTO);
        return "";
    }

}
