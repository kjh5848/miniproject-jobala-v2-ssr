package com.example.jobala.apply;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ApplyRequest {

    @Data
    @AllArgsConstructor
    public static class ApplyRequestDTO {
        private Integer resumeId;
        private Integer jobopenId;
        private Integer userId;
    }
}