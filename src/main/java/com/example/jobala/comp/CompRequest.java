package com.example.jobala.comp;

import lombok.Data;

import java.sql.Date;

public class CompRequest {

    // FIX: 파스칼 표기법 필요
    @Data
    public static class ScoutListDTO{
        private String name;
        private String resumeTitle;
        private Date age;
        private String address;
        private String career;
    }


}
