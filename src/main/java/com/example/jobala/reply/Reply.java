package com.example.jobala.reply;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Table(name="reply_tb")
@Data
@Entity // 테이블 생성하기 위해 필요한 어노테이션
public class Reply {
    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 전략
    private int id;

    @Column(length = 100) // 제약 조건 걸기
    private String comment;
    private int boardId; // 테이블에 만들어 질때 board_id
    private String username; // 테이블에 만들어 질때 user_id
    private LocalDateTime createdAt;
}

