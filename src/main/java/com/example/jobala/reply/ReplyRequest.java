package com.example.jobala.reply;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ReplyRequest {
    @AllArgsConstructor
    @Data
    public static class ReplyDTO{
        private Integer boardId;
        private String content;
    }
}
