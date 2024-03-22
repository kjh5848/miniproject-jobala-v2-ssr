package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala._core.errors.exception.Exception403;
import com.example.jobala._core.errors.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JobopenService {

    private final JobopenJPARepository jobopenJPARepository;

    @Transactional
    public Jobopen 공고등록(JobopenRequest.SaveDTO reqDTO, User sessionUser) {
        Jobopen jobopen = jobopenJPARepository.save(reqDTO.toEntity(sessionUser));
        return null;
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
