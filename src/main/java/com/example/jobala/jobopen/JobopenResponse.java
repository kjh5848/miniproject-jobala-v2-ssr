package com.example.jobala.jobopen;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class JobopenResponse {
    @AllArgsConstructor
    @Data
    public static class ListDTO {
        private Integer id;
        private String jobopenTitle;
        private String compLocation;
        private String career;
        private String edu;
        private String imgFilename;
    }

    @Data
    public static class DTO {
        private Integer id;
        private Integer userId;
        private String compname; //회사명
        private String jobopenTitle; //공고제목
        private String content; //내용
        private String career;// 경력
        private String edu; // 학력
        private String hopeJob; //희망직종
        private String compLocation; //근무지역
        private String jobType; // 고용형태
        private String salary; //연봉
        private java.sql.Date endTime; // 마감일
        private Integer role; // 역할 0 -> guest, 1 -> comp
        private int count;


        public DTO(Jobopen jobopen) {
            this.id = id;
            this.userId = userId;
            this.compname = compname;
            this.jobopenTitle = jobopenTitle;
            this.content = content;
            this.career = career;
            this.edu = edu;
            this.hopeJob = hopeJob;
            this.compLocation = compLocation;
            this.jobType = jobType;
            this.salary = salary;
            this.endTime = endTime;
            this.role = role;
            this.count = count;
        }

        @CreationTimestamp
        private Timestamp createdAt; //생성일


    }

    @AllArgsConstructor
    @Data
    public static class JobopenDetailDTO {
        private String jobopenTitle;
        private String compname;
    }

    @AllArgsConstructor
    @Data
    public static class SearchDTO {
        private Integer id;
        private String jobopenTitle;
        private String compLocation;
        private String career;
        private String edu;
        private Date endTime;
    }


}



