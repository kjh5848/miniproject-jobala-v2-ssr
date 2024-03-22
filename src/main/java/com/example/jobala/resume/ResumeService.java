package com.example.jobala.resume;

import com.example.jobala._core.errors.exception.Exception403;
import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._user.User;
import com.example.jobala.skill.Skill;
import com.example.jobala.skill.SkillJPARepository;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeJPARepository resumeJPARepository;
    private final SkillJPARepository skillJPARepository;

    //TODO: 포스트맨으로 권한체크하기
    @Transactional
    public Resume 이력서등록(ResumeRequest.SaveDTO reqDTO, User sessionUser) {
        Resume resume = resumeJPARepository.save(reqDTO.toEntity(sessionUser));

        List<String> skills = reqDTO.getSkills();

        String json = new Gson().toJson(skills);
        Skill skill = Skill.builder()
                .role(0)
                .name(json)
                .resume(resume)
                .build();

        skillJPARepository.save(skill);
        return resume;
    }

    @Transactional
    public Resume resumeDelete(int resumeId, Integer sessionUserId) {
        Resume resume = resumeJPARepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));
        if (sessionUserId != resume.getUser().getId()) {
            throw new Exception403("게시글을 삭제할 권한이 없습니다");
        }
        resumeJPARepository.deleteById(resumeId);
        return resume;
    }

    @Transactional
    public Resume 이력서수정(int resumeId, ResumeRequest.UpdateDTO reqDTO) {
        Resume resume = resumeJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("이력서 정보를 찾을 수 없습니다."));
        resume.setResumeTitle(reqDTO.getResumeTitle());
        resume.setHopeJob(reqDTO.getHopeJob());
        resume.setCareer(reqDTO.getCareer());
        resume.setLicense(reqDTO.getLicense());
        resume.setContent(reqDTO.getContent());
        resume.setEdu(reqDTO.getEdu());

        List<String> skills = reqDTO.getSkills();
        String json = new Gson().toJson(skills);
        System.out.println("제이슨 결과 = " + json);

        resume.getSkill().setName(json);
        return resume;
    }

    public Resume 이력서조회(Integer id) {
        return resumeJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다."));
    }
}
