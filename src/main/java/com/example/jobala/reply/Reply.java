package com.example.jobala.reply;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Table(name="reply_tb")
@Data
@Entity // 테이블 생성하기 위해 필요한 어노테이션
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String comment;
    private Integer boardId;//외래키.
    private String username;
    private LocalDateTime createdAt;
}

