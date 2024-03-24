package com.example.jobala.reply;

import com.example.jobala._user.User;
import com.example.jobala.board.Board;
import lombok.Data;

public class ReplyRequest {
    @Data
    public static class SaveDTO {
        private String comment;
        private Integer boardId;

        public Reply toEntity(User sessionUser, Board board){
            return Reply.builder()
                    .comment(comment)
                    .board(board)
                    .user(sessionUser)
                    .build();
        }
    }


}