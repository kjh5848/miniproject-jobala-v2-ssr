package com.example.jobala.reply;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ReplyResponse {
    @AllArgsConstructor
    @Data
    public static class ReplyListDTO{
        private Integer boardId;
        private String username;
        private String comment;
    }
}
