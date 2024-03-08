package com.example.jobala.jobopen;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.swing.plaf.PanelUI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobopenResponse {
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
            private LocalDateTime createdAt; //생성일
            private Integer role; // 역할 0 -> guest, 1 -> comp
            private int count;

        public DTO(Jobopen jobopen) {
            this.id = jobopen.getId();
            this.compname = jobopen.getCompname();
            this.jobopenTitle = jobopen.getJobopenTitle();
            this.content = jobopen.getContent();
            this.career = jobopen.getCareer();
            this.edu = jobopen.getEdu();
            this.hopeJob = jobopen.getHopeJob();
            this.compLocation = jobopen.getCompLocation();
            this.jobType = jobopen.getJobType();
            this.salary = jobopen.getSalary();
            this.endTime = jobopen.getEndTime();
            this.createdAt = jobopen.getCreatedAt();
            this.role = jobopen.getRole();
        }
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

    @Data
    public static class DetailDTO {
        private Integer id;
        private String compname; //대표명
        private String jobopenTitle; //공고제목
        private String career;// 경력
        private String edu; // 학력
        private String jobType; // 고용형태
        private String salary; //연봉
        private String compLocation; //근무지역
        private String content; //내용
        private String hopeJob; //내용
        private String name;

    }

    //사진
    //맞춤 공고
    // 사진
    // 회사명
    // 공고타이틀

}



