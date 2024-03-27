package com.example.jobala.apply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

public class ApplyResponse {

   //기업 applyForm 응답DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompApplyDTO {
        private Integer id;
        private Integer jobopenId;
        private Integer resumeId;
        private String jobopenTitle;
        private String resumeTitle;
        private String name;
        private String edu;
        private Date endTime;
        private String state;
    }

    // 개인 applyForm 응답DTO
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GuestApplyDTO {
        private Integer id;
        private Integer resumeId;
        private Integer jobopenId;
        private String jobopenTitle;
        private String resumeTitle;
        private String compname;
        private Date endTime;
        private String state;
    }

    //기업 positionForm 응답DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompPositionDTO {
        private Integer id;
        private Integer jobopenId;
        private Integer resumeId;
        private String jobopenTitle;
        private String resumeTitle;
        private String name;
        private String edu;
        private Date endTime;
        private String state;
    }

    //개인 positionForm 응답DTO
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GuestPositionDTO {
        private Integer id;
        private Integer jobopenId;
        private Integer resumeId;
        private String jobopenTitle;
        private String resumeTitle;
        private String name;
        private String edu;
        private Date endTime;
        private String state;
    }
}

