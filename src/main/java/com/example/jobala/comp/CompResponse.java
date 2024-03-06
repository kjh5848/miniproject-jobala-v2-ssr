package com.example.jobala.comp;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

public class CompResponse {

    @Data
    @AllArgsConstructor
    public static class ScoutListDTO{
        private String name;
        private String resumeTitle;
        private Date age;
        private String address;
        private String career;
    }

    @Data
    public static class ResumeListDTO{
        private Integer id;
        private String resumeTitle;
        private String career;
        private String edu;

        public ResumeListDTO(Integer id, String resumeTitle, String career, String edu) {
            this.id = id;
            this.resumeTitle = resumeTitle;
            this.career = career;
            this.edu = edu;
        }
    }
}
