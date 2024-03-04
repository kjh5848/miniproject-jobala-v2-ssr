package com.example.jobala.comp;

import lombok.Data;

import java.sql.Date;

public class CompRequest {

    @Data
    public static class scoutListDTO{
        private String name;
        private String resumeTitle;
        private Date age;
        private String address;
        private String career;
    }


}
