package com.example.jobala.scrap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ScrapRequest {

    // 회사가 이력서를 스크랩
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CompScrap {
        private Integer resumeId;
    }


    // 개인이 공고를 스크랩
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class GuestScrap {
        private Integer jobopenId;
    }
}
