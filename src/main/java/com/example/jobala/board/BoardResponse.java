package com.example.jobala.board;

import com.example.jobala._user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

public class BoardResponse {
    //    자유게시판 메인 목록 DTO
    @AllArgsConstructor
    @Data
    public static class MainDetailDTO {
        private Integer id;
        private Integer userId;
        private String title;
        private Timestamp createdAt; // LocalDateTime을 사용하면 안된다.
        private String username;
    }


    @Data
    public static class BoardDetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private String username;
        private Boolean boardOwner;

        public BoardDetailDTO(Integer id, String title, String content, Integer userId, String username) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.userId = userId;
            this.username = username;
        }

        public void isOwner(User sessionUser) {
            if (sessionUser == null) boardOwner = false;
            else boardOwner = sessionUser.getId() == userId;
        }

    }


}
