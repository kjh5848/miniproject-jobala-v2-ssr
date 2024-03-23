package com.example.jobala.resume;

import com.example.jobala._user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ResumeRequest {

    @AllArgsConstructor
    @Data
    public static class SaveDTO {
        private String resumeTitle;
        private String hopeJob;
        private String career;
        private String license;
        private String content;
        private String edu;

        private List<String> skills = new ArrayList<>();

        public Resume toEntity(User user) {
            return Resume.builder()
                    .resumeTitle(resumeTitle)
                    .hopeJob(hopeJob)
                    .career(career)
                    .license(license)
                    .content(content)
                    .edu(edu)
                    .skills(String.valueOf(skills))
                    .user(user)
                    .role(user.getRole())
                    .name(user.getName())
                    .build();
        }
    }

    @AllArgsConstructor
    @Data
    public static class DeleteDTO {
        private int id;
    }

    @AllArgsConstructor
    @Data
    public static class UpdateDTO {
        private String resumeTitle;
        private String hopeJob;
        private String career;
        private String license;
        private String content;
        private String edu;
        private List<String> skills = new ArrayList<>();
    }
}