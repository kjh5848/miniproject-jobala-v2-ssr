package com.example.jobala.reply;

import lombok.Data;

public class ReplyRequest {
    @Data
    public static class WriteDTO {
        private String comment;
        private Integer boardId;
    }
}
