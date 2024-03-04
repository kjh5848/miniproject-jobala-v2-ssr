package com.example.jobala.jobopen;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "jobopen_tb")
public class Jobopen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String compname; //회사명
    private String jobopenTitle; //공고제목
    private String content; //내용
    private String career;// 경력
    private String edu; // 학력
    private String hopeJob; //희망직종
    private String compLocation; //근무지역
    private String jobType; // 고용형태
    private String salary; //연봉
    private Date endTime; // 마감일
    private String skills; // 마감일

    private LocalDateTime createdAt; //생성일

    @ColumnDefault("1")
    private Integer role; // 역할 0 -> guest, 1 -> comp
}