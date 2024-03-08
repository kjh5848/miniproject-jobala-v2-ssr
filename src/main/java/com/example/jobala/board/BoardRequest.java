package com.example.jobala.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
    }
}
