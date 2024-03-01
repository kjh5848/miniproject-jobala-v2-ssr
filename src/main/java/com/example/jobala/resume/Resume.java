package com.example.jobala.resume;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "resume_tb")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private String title;
    private String hopeJob;
    private String career;
    private String license;
    private String content;
    private LocalDateTime createdAt;
}
