package com.example.jobala.resume;

import com.example.jobala._core.errors.exception.Exception403;
import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala.board.Board;
import com.example.jobala.skill.Skill;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeJPARepository resumeJPARepository;

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
}
