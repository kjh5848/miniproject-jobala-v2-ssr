package com.example.jobala.jobopen;

import com.example.jobala._core.errors.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobopenService {
    private final JobopenJPARepository jobopenJPARepository;

    public Jobopen jobopenDetail(Integer jobopenId) {
        Jobopen jobopen = jobopenJPARepository.findByIdWithUser(jobopenId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));
        return jobopen;
    }
}