package com.example.jobala.jobopen;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class JobopenResponse {
    
    @AllArgsConstructor
    @Data
    public static class DetailDTO {
        private String compname; //대표명
        private String jobopenTitle; //공고제목
        private String career;// 경력
        private String edu; // 학력
        private String jobType; // 고용형태
        private String salary; //연봉
        private String compLocation; //근무지역
        private String content; //내용
        private List<String> skills = new ArrayList<>(); //내용

        //사진
        //맞춤 공고
        // 사진
        // 회사명
        // 공고타이틀

    }


}
