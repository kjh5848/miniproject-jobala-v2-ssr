package com.example.jobala.apply;

import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "apply_tb")

public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer resumeId;
    private Integer userId; // 기업일수도 있고, 개인일 수도 있다.
    private Integer jobopenId;
    private String state; // 열람전, 합격, 불합격
    private Integer role; // 0 -> guest, 1 -> comp
    private Timestamp createdAt;
}
