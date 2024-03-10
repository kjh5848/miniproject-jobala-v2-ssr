package com.example.jobala.resume;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ResumeResponse {
    @AllArgsConstructor
    @Data
    public static class ListDTO {
        private int id;
        private String name;
        private String resumeTitle;
        private String edu;
        private String career;
        private String imgFilename;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class DetailDTO {
        private Integer userId;
        private String resumeTitle;
        private String hopeJob;
        private String career;
        private String license;
        private String content;
        private String edu;
        private LocalDateTime createdAt;
    }
}
