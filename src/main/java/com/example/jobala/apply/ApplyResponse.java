package com.example.jobala.apply;

import lombok.Data;

public class ApplyResponse {

    @Data
    public static class ApplyDTO {
        private Integer id;
        private String jobopenTitle;
        private String resumeTitle;
        private String name;

        public ApplyDTO(Integer id, String jobopenTitle, String resumeTitle, String name) {
            this.id = id;
            this.jobopenTitle = jobopenTitle;
            this.resumeTitle = resumeTitle;
            this.name = name;
        }
    }
}