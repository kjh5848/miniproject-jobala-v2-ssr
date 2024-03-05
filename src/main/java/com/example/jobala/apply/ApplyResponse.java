package com.example.jobala.apply;

import com.example.jobala.resume.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

public class ApplyResponse {

    @Data
    public static class ApplyDTO {
        private Integer id;
        private String jobopenTitle;
        private String resumeTitle;
        private String name;

        public ApplyDTO(Integer id, String jobopenTitle, String resumeTitle, String name) {
            this.id = id;
            this.jobopenTitle = jobopenTitle;
            this.resumeTitle = resumeTitle;
            this.name = name;
        }
    }

    @AllArgsConstructor
    @Data
    public static class CardDetailDTO {
        private String jobopenTitle; // 채용공고 제목
        private String resumeTitle; // 이력서 제목
        private String name; // 지원자 이름
        private String edu; // 학력
        private LocalDateTime endTime; // 마감일
        private String state; // 지원자 합격 불합격 상태

    }

    @AllArgsConstructor
    @Data
    public static class positionDetailDTO {
        private String name; // 지원자 이름
        private String resumeTitle; // 이력서 제목
        private String jobopenTitle; // 공고 제목
        private String status; // 지원자 제안중 상태

    }
}