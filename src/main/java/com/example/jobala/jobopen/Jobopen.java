package com.example.jobala.jobopen;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "jobopen_tb")
public class Jobopen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private String title;
    private String content;
    private String career;
    private String edu;
    private String hopeJob;
    private String compLocation;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private Integer role; // 0 -> guest, 1 -> comp
}