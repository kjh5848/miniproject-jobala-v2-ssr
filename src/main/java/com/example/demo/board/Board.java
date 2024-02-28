package com.example.demo.board;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "board_tb")

public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
