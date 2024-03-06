package com.example.jobala.jobopen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobopenResponse {

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



