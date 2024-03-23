package com.example.jobala.resume;

import com.example.jobala._core.errors.exception.Exception403;
import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._user.User;
import com.example.jobala.skill.SkillJPARepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeJPARepository resumeJPARepository;
    private final SkillJPARepository skillJPARepository;

    @Transactional
    public Resume 이력서등록(ResumeRequest.SaveDTO reqDTO, User sessionUser) {
        return resumeJPARepository.save(reqDTO.toEntity(sessionUser));
    }

    @Transactional
    public Resume 이력서삭제(int resumeId, Integer sessionUserId) {
        Resume resume = resumeJPARepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));
        if (sessionUserId != resume.getUser().getId()) {
            throw new Exception403("이력서를 삭제할 권한이 없습니다");
        }
        resumeJPARepository.deleteById(resumeId);
        return resume;
    }

    @Transactional
    public Resume 이력서수정(Integer resumeId, ResumeRequest.UpdateDTO reqDTO, Integer sessionUserId) {
        Resume resume = resumeJPARepository.findById(resumeId)
                .orElseThrow(() -> new Exception404("이력서 정보를 찾을 수 없습니다."));
        if (sessionUserId != resume.getUser().getId()) {
            throw new Exception403("이력서를 삭제할 권한이 없습니다.");
        }
        resume.setResumeUpdateDTO(reqDTO);
        return resume;
    }

    public Resume 이력서보기(Integer id) {
        return resumeJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다."));
    }
}
