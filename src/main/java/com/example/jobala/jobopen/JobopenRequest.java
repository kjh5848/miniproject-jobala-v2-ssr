package com.example.jobala.jobopen;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

public class JobopenRequest {
    @AllArgsConstructor
    @Data
    public static class WriteDTO {
        private Integer role;
        private String edu;
        private String jobopenTitle;
        private String career;
        private String jobType;
        private String salary;
        private String hopeJob;
        private String compLocation;
        private String content;
        private Date endTime;
        private LocalDateTime createdAt;
        private String[] skills;

    }

    @AllArgsConstructor
    @Data
    public static class SkillDTO {
        private Integer role;
        private Integer resumeId;
        private Integer jobOpenId;
        private String[] skills;
    }
}
