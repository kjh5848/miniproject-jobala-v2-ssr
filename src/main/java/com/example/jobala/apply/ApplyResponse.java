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

    //뭐??

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApplyDTO {
        private Integer id;
        private String jobopenTitle;
        private String resumeTitle;
        private String name;
        private String edu;
        private Date endTime;
        private String state;
        private Integer resumeId;
    }

    //뭐??
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApplyDTO2 {
        private Integer id;
        private String jobopenTitle;
        private String resumeTitle;
        private String edu;
        private Date endTime;
        private String state;
        private Integer jobopenId;
    }
}