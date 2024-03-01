package com.example.jobala.scrap;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "scrap_tb")
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int jobOpenId;
    private int resumeId;
    private Boolean role;
    private LocalDateTime createAt;
}
