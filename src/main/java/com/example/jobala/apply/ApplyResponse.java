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
        private String career;

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
        private Date endTime;
        private String state;

        public GuestApplyDTO(Apply apply) {
            this.id = apply.getId();
            this.resumeId = apply.getResume().getId();
            this.jobopenId = apply.getJobopen().getId();
            this.jobopenTitle = apply.getJobopen().getJobopenTitle();
            this.resumeTitle = apply.getResume().getResumeTitle();
            this.endTime = apply.getJobopen().getEndTime();
            this.state = apply.getState();
        }
    }

    //기업 positionForm 응답DTO
    @Data
    @NoArgsConstructor
    public static class CompPositionDTO {
        private Integer id;
        private Integer jobopenId;
        private Integer resumeId;
        private String jobopenTitle;
        private String resumeTitle;
        private String name;
        private String state;

        public CompPositionDTO(Apply apply) {
            this.id = apply.getId();
            this.jobopenId = apply.getJobopen().getId();
            this.resumeId = apply.getResume().getId();
            this.jobopenTitle = apply.getJobopen().getJobopenTitle();
            this.resumeTitle = apply.getResume().getResumeTitle();
            this.name = apply.getResume().getUser().getName();
            this.state = apply.getState();
        }
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
        private Date endTime;
        private String state;
        private String salary;
    }
}

