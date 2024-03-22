package com.example.jobala.Pic;

import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "pic_tb")
@Entity
public class Pic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    private Resume resume;
    @OneToOne(fetch = FetchType.LAZY)
    private Jobopen jobopen;
    private String title;
    private String imgFilename; // 파일 패스
}

