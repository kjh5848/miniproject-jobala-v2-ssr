package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala._core.errors.exception.Exception403;
import com.example.jobala._core.errors.exception.Exception404;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobopenService {

    private final JobopenJPARepository jobopenJPARepository;

    @Transactional
    public Jobopen 공고등록(JobopenRequest.SaveDTO reqDTO, User sessionUser) {
        Jobopen jobopen = jobopenJPARepository.save(reqDTO.toEntity(sessionUser));
        return jobopen;
    }

    @Transactional
    public Jobopen 공고삭제(Integer jobopenId, Integer sessionUserId) {
        Jobopen jobopen = jobopenJPARepository.findById(jobopenId)
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다."));

        if (sessionUserId != jobopen.getUser().getId()) {
            throw new Exception403("공고를 삭제할 권한이 없습니다.");
        }
        jobopenJPARepository.deleteById(jobopenId);
        return jobopen;
    }

    @Transactional
    public Jobopen 공고수정하기(int jobOpenId, int sessionUser, JobopenRequest.UpdateDTO reqDTO) {
        //1.조회 및 예외 처리
        Jobopen jobopen = jobopenJPARepository.findById(jobOpenId)
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다."));
        //2.권한 처리
        if (sessionUser != jobopen.getUser().getId()) {
            throw new Exception403("공고를 수정할 권한이 없습니다.");
        }
        //3.공고 수정
        jobopen.setJobopenUpdate(reqDTO);
        return jobopen;
    }

    public Jobopen 공고보기(Integer jobopenId) {
        Jobopen jobopen = jobopenJPARepository.findById(jobopenId)
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다"));
        return jobopen;
    }
}
