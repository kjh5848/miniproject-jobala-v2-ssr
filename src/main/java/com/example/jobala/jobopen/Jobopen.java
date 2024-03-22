package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala.skill.Skill;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Data
@Table(name = "jobopen_tb")
public class Jobopen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
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

    @CreationTimestamp
    private Timestamp createdAt; //생성일

    @ColumnDefault("1")
    private Integer role; // 역할 0 -> guest, 1 -> comp

    @OneToOne(mappedBy = "jobopen",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Skill skill;

    @Builder
    public Jobopen(Integer id, User user, String compname, String jobopenTitle, String content, String career, String edu, String hopeJob, String compLocation, String jobType, String salary, Date endTime, Integer role) {
        this.id = id;
        this.user = user;
        this.compname = compname;
        this.jobopenTitle = jobopenTitle;
        this.content = content;
        this.career = career;
        this.edu = edu;
        this.hopeJob = hopeJob;
        this.compLocation = compLocation;
        this.jobType = jobType;
        this.salary = salary;
        this.endTime = endTime;
        this.role = role;
    }

    //    @Transient
//    private Integer count;
}