package com.example.jobala.apply;

import lombok.Data;

import java.sql.Date;

public class ApplyRequest {

    @Data
    public static class UpdateDTO {
        private Integer state;
        private Long jobopenId; // 채용공고 ID
        private String name; // 지원자 이름
        private String resumeTitle; // 이력서 제목
        private Date endTime;
    }
}
