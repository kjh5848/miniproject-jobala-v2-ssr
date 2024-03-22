package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala._core.errors.exception.Exception403;
import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala.skill.Skill;
import com.example.jobala.skill.SkillJPARepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobopenService {

    private final JobopenJPARepository jobopenJPARepository;
    private final SkillJPARepository skillJPARepository;

    @Transactional
    public Jobopen 공고등록(JobopenRequest.SaveDTO reqDTO, User sessionUser) {
        Jobopen jobopen = jobopenJPARepository.save(reqDTO.toEntity(sessionUser));

        List<String> skills = reqDTO.getSkills();
        String json = new Gson().toJson(skills);
        System.out.println("제이슨 결과 = " + json);

        Skill skill = Skill.builder()
                .role(1)
                .name(json)
                .jobopen(jobopen)
                .build();

        skillJPARepository.save(skill);
        return jobopen;
    }

    @Transactional
    public Jobopen 공고삭제(Integer jobopenId, Integer sessionUserId) {
        Jobopen jobopen = jobopenJPARepository.findById(jobopenId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        if (sessionUserId != jobopen.getUser().getId()) {
            throw new Exception403("게시글을 삭제할 권한이 없습니다.");
        }
        jobopenJPARepository.deleteById(jobopenId);
        return jobopen;
    }


    public Jobopen jobopenDetail(Integer jobopenId) {
        Jobopen jobopen = jobopenJPARepository.findByIdWithUser(jobopenId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));
        return jobopen;
    }
}
