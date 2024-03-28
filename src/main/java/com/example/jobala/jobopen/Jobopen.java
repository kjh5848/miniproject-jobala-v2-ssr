package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala.apply.Apply;
import com.example.jobala.scrap.Scrap;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    private String jobopenTitle; //공고제목
    private String career;// 경력
    private String edu; // 학력
    private String hopeJob; //희망직종
    private String compLocation; //근무지역
    private String jobType; // 고용형태
    private String salary; //연봉
    private Date endTime; // 마감일
    private String skills;

    @ColumnDefault("1")
    private Integer role; // 역할 0 -> guest, 1 -> comp

    @CreationTimestamp
    private Timestamp createdAt; //생성일

    @OneToMany(mappedBy = "jobopen", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Apply> applies = new ArrayList<>();

    @OneToMany(mappedBy = "jobopen", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Scrap> scraps;

    @Builder
    public Jobopen(Integer id, User user, String jobopenTitle, String career, String edu, String hopeJob, String compLocation, String jobType, String salary, Date endTime, String skills, Integer role, Timestamp createdAt, List<Apply> applies, List<Scrap> scraps) {
        this.id = id;
        this.user = user;
        this.jobopenTitle = jobopenTitle;
        this.career = career;
        this.edu = edu;
        this.hopeJob = hopeJob;
        this.compLocation = compLocation;
        this.jobType = jobType;
        this.salary = salary;
        this.endTime = endTime;
        this.skills = skills;
        this.role = role;
        this.createdAt = createdAt;
        this.applies = applies;
        this.scraps = scraps;
    }

    public void setJobopenUpdate(JobopenRequest.UpdateDTO reqDTO) {
        this.jobopenTitle = reqDTO.getJobopenTitle();
        this.career = reqDTO.getCareer();
        this.edu = reqDTO.getEdu();
        this.jobType = reqDTO.getJobType();
        this.salary = reqDTO.getSalary();
        this.hopeJob = reqDTO.getHopeJob();
        this.compLocation = reqDTO.getCompLocation();
        this.endTime = Date.valueOf(reqDTO.getEndTime());
        this.skills = String.valueOf(reqDTO.getSkills());
    }
}
