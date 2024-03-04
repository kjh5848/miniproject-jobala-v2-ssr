package com.example.jobala.apply;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

public class ApplyRequest {

    @AllArgsConstructor
    @Data
    public static class UpdateDTO {

        private Long jobopenId; // 채용공고 ID

    }

    @AllArgsConstructor
    @Data
    public static class ProfileDTO {

        private String name;
        private String resumeTitle;
        private String education;
        private String jobTitle;

    }

}
