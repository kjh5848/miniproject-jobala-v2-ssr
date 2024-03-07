package com.example.jobala.scrap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ScrapRequest {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CompScrap { // 회사가 이력서를 스크랩
        private Integer resumeId;
    }
}
