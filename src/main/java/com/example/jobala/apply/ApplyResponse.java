package com.example.jobala.apply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

public class ApplyResponse {

    @Data
    public static class ApplyStatusDTO {
        private List<ApplyResponse.ApplyDTO> appliedPositions;
        private List<ApplyResponse.ApplyDTO2> receivedOffersReviewing;
        private List<ApplyResponse.ApplyDTO2> receivedOffersAccepted;
        private List<ApplyResponse.ApplyDTO2> receivedOffersRejected;

        public ApplyStatusDTO(List<ApplyResponse.ApplyDTO> appliedPositions, List<ApplyResponse.ApplyDTO2> receivedOffersReviewing, List<ApplyResponse.ApplyDTO2> receivedOffersAccepted, List<ApplyResponse.ApplyDTO2> receivedOffersRejected) {
            this.appliedPositions = appliedPositions;
            this.receivedOffersReviewing = receivedOffersReviewing;
            this.receivedOffersAccepted = receivedOffersAccepted;
            this.receivedOffersRejected = receivedOffersRejected;
        }
    }


    @Data
    public static class ApplyDTO {
        private Integer id;
        private String jobopenTitle;
        private String resumeTitle;
        private String name;
        private String edu;
        private Date endTime;
        private String state;
        private Integer resumeId;

        public ApplyDTO(Integer id, String jobopenTitle, String resumeTitle, String name, String edu, Date endTime, String state, Integer resumeId) {
            this.id = id;
            this.jobopenTitle = jobopenTitle;
            this.resumeTitle = resumeTitle;
            this.name = name;
            this.edu = edu;
            this.endTime = endTime;
            this.state = state;
            this.resumeId = resumeId;
        }
    }

    @Data
    public static class ApplyDTO2 {
        private Integer id;
        private String jobopenTitle;
        private String resumeTitle;
        private String compname;
        private String edu;
        private Date endTime;
        private String state;
        private Integer jobopenId;

        public ApplyDTO2(Integer id, String jobopenTitle, String resumeTitle, String compname, String edu, Date endTime, String state, Integer jobopenId) {
            this.id = id;
            this.jobopenTitle = jobopenTitle;
            this.resumeTitle = resumeTitle;
            this.compname = compname;
            this.edu = edu;
            this.endTime = endTime;
            this.state = state;
            this.jobopenId = jobopenId;
        }
    }
}