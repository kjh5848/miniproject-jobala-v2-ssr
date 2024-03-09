package com.example.jobala.reply;

import lombok.Data;

import lombok.Data;

public class ReplyRequest {


    @Data
    public static class SaveDTO {
        private int id;
        private String comment;
        private String userName;
        private int boardId;
    }
}
