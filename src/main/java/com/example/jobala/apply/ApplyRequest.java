package com.example.jobala.apply;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.print.attribute.standard.JobPriority;

public class ApplyRequest {

    @Data
    @AllArgsConstructor
    public static class ApplyRequestDTO {
        private Integer resumeId;
        private Integer jobopenId;
        private Integer userId;
        private String state; // 열람전, 합격, 불합격

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