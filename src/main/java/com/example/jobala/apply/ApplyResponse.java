package com.example.jobala.apply;

import lombok.Data;

import java.time.LocalDateTime;

public class ApplyResponse {
    @Data
    public static class CardDetailDTO {
        private String name; // 지원자 이름
        private String jobopenTitle; // 공고 제목
        private String title; // 이력서 제목
        private String edu; // 학력
        private String address; // 주소
        private String state; // 지원자 합격 불합격 상태
        private LocalDateTime createdAt; // 지원일시
    }

    @Data
    public static class positionDetailDTO {
        private String name; // 지원자 이름
        private String resumeTitle; // 이력서 제목
        private String jobopenTitle; // 공고 제목
        private String status; // 지원자 제안중 상태

    }
}