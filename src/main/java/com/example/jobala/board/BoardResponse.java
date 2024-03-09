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

    // 자유게시판 상세페이지
    @Data
    public static class BoardDetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private String username;
        private Boolean boardOwner;

        // qlrm 맵핑은 순서를 맞춰주고 조인하는 컬럼만 생성자를 만들어서 초기화를 시켜준다.
        public BoardDetailDTO(Integer id, String title, String content, Integer userId, String username) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.userId = userId;
            this.username = username;
        }

        // 보더의 주인여부 확인
        public void isOwner(User sessionUser) {
            if (sessionUser == null) boardOwner = false;
            else boardOwner = sessionUser.getId() == userId;
        }

    }


}