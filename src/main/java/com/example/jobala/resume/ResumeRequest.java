package com.example.jobala.resume;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.skill.Skill;
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
                    .user(user)
                    .resumeTitle(resumeTitle)
                    .license(license)
                    .edu(edu)
                    .career(career)
                    .hopeJob(hopeJob)
                    .content(content)
                    .skill(Skill.builder().role(1).name(String.join(",", skills)).build())
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
