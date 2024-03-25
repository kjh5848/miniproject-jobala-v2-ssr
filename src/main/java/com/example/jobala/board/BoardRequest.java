package com.example.jobala.board;

import com.example.jobala._user.User;
import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private String title;
        private String content;

        public Board toEntity(User sessionUser) {
            Board board = Board.builder()
                    .title(title)
                    .content(content)
                    //유저 객체 받아오기
                    .user(sessionUser)
                    .build();
            return board;
        }
    }
    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
    }
}