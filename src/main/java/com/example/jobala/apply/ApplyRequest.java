package com.example.jobala.apply;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;

public class ApplyRequest {

    @Data
    @AllArgsConstructor
    public static class ApplyRequestDTO {
        private Integer resumeId;
        private Integer jobopenId;
        private Integer userId;

        public Apply toEntity(Resume resume, Jobopen jobopen, User sessionUser) {
            return Apply.builder()
                    .role(sessionUser.getRole())
                    .user(sessionUser)
                    .jobopen(jobopen)
                    .resume(resume)
                    .state("검토중")
                    .build();
        }
    }
}