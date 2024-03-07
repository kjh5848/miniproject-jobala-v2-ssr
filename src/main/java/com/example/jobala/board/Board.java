package com.example.jobala.board;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "board_tb")

public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String content;
    private Integer role; // 0 -> guest, 1 -> comp
    private Integer userId;
    private LocalDateTime createdAt;
}
