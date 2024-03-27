package com.example.jobala.resume;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ResumeResponse {

    @AllArgsConstructor
    @Data
    public static class ScrapDTO{
        private int id;
        private String name;
        private String resumeTitle;
        private String career;
        private String edu;

        public ScrapDTO(Resume resume) {
            this.id = resume.getId();
            this.name = resume.getUser().getName();
            this.resumeTitle = resume.getResumeTitle();
            this.career = resume.getCareer();
            this.edu = resume.getEdu();
        }
    }

    @AllArgsConstructor
    @Data
    public static class ListDTO {
        private int id;
        private String name;
        private String resumeTitle;
        private String edu;
        private String career;
        private String imgFilename;

        public ListDTO(Resume resume) {
            this.id = id;
            this.name = resume.getUser().getName();
            this.resumeTitle = resume.getResumeTitle();
            this.edu = resume.getEdu();
            this.career = resume.getCareer();
            this.imgFilename = resume.getUser().getImgFilename();
        }
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
