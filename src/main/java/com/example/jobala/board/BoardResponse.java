package com.example.jobala.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BoardResponse {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class DetailDTO{
        private Integer id;
        private String title;
        private Timestamp createdAt; // LocalDateTime을 사용하면 안된다.
        private String username;
    }
}
