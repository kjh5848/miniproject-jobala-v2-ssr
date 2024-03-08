package com.example.jobala.reply;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reply_tb")

public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;
    private Integer board_id;//외래키.
    private Integer user_id;
}
