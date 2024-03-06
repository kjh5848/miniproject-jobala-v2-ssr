package com.example.jobala.Pic;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "pic_tb")
@Entity
public class Pic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String imgFilename; // 파일 패스
}

