package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala.skill.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class JobopenRequest {

    @AllArgsConstructor
    @Data
    public static class DeleteDTO {
        private int id;
    }

    @AllArgsConstructor
    @Data
    public static class JobopenDetailDTO {
        private String edu;
        private String jobopenTitle;
        private String career;
        private String jobType;
        private String salary;
        private String hopeJob;
        private String compLocation;
        private String content;
        private Date endTime;
        private List<String> skills = new ArrayList<>(); //내용
    }

    @AllArgsConstructor
    @Data
    public static class UpdateDTO {
        //compname = ? ,jobopenTitle=? , career=?, edu=?, jobType=?,salary=?,compLocation=?,content=? ,skills =?
        private String compname; //대표명
        private String jobopenTitle; //공고제목
        private String career;// 경력
        private String edu; // 학력
        private String jobType; // 고용형태
        private String salary; //연봉
        private String compLocation; //근무지역
        private String content; //내용
        private String endTime; //내용
        private List<String> skills = new ArrayList<>();
    }

    @AllArgsConstructor
    @Data
    public static class SaveDTO {
        private String edu;
        private String jobopenTitle;
        private String career;
        private String jobType;
        private String salary;
        private String hopeJob;
        private String compLocation;
        private String content;
        private Date endTime;
        private List<String> skills = new ArrayList<>(); //내용

        public Jobopen toEntity(User user) {
            return Jobopen.builder()
                    .user(user)
                    .edu(edu)
                    .jobopenTitle(jobopenTitle)
                    .career(career)
                    .jobType(jobType)
                    .salary(salary)
                    .hopeJob(hopeJob)
                    .compLocation(compLocation)
                    .content(content)
                    .endTime(endTime)
<<<<<<< HEAD
                    .skill(Skill.builder().role(1).name(String.join(",", skills)).build())
=======
>>>>>>> pch/jobopen/delete
                    .build();
        }
    }
}


