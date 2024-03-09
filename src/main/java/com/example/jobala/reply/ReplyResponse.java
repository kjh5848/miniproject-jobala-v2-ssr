package com.example.jobala.reply;

import com.example.jobala._user.User;
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

    @AllArgsConstructor
    @Data
    public static class ReplyDeleteDTO {
        private Integer boardId;
        private String username;
        private String comment;
        private Boolean replyOwner; //게시글 주인 여부
    }
}
