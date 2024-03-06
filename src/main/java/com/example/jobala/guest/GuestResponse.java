package com.example.jobala.guest;


import lombok.Data;

import java.sql.Date;

public class GuestResponse {
    @Data
    public static class GuestDTO {
        private Integer id;
        private String jobopenTitle;
        private String compLocation;
        private String career;
        private String edu;
        private Date endTime;


        public GuestDTO(Integer id, String jobopenTitle, String compLocation, String career, String edu, Date endTime) {
            this.id = id;
            this.jobopenTitle = jobopenTitle;
            this.compLocation = compLocation;
            this.career = career;
            this.edu = edu;
            this.endTime = endTime;
        }
    }
}
