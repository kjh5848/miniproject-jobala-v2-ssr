package com.example.jobala.reply;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reply_tb")

public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;
    private Integer boardId;//외래키.
    private Integer userId;
    private LocalDateTime createdAt;
}
