package com.example.jobala.comp;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

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
    @AllArgsConstructor
    public static class ResumeDTO {
        private int id;
        private String resumeTitle;
        private String career;
        private String education;
    }

}
