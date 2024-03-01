package com.example.jobala.jobOpen;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "job_open_tb")
public class JobOpen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private String title;
    private String content;
    private String career;
    private String edu;
    private String hopeJob;
    private String compLocation;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
}