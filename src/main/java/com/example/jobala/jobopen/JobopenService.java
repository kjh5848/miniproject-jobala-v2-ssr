package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobopenService {

    private final JobopenJPARepository jobopenJPARepository;

    public Jobopen 공고등록(JobopenRequest.SaveDTO reqDTO, User sessionUser) {
        Jobopen jobopen = jobopenJPARepository.save(reqDTO.toEntity(sessionUser));
        return null;
    }

}

