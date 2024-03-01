package com.example.jobala.board;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "board_tb")

public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String content;

    private int userId;
}
