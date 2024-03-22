package com.example.jobala.resume;

import com.example.jobala._user.User;
import com.example.jobala.apply.Apply;
import com.example.jobala.scrap.Scrap;
import com.example.jobala.skill.Skill;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@Table(name = "resume_tb")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private String name; // 지원자 이름
    private String resumeTitle;
    private String hopeJob;
    private String career;
    private String license;
    private String content;
    private String edu;

    @ColumnDefault("0")
    private Integer role; // 0 -> guest, 1 -> comp

    @OneToOne(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Skill skill;

    @OneToMany(mappedBy = "resume",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Apply> applies = new ArrayList<>();

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Scrap> scraps;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Resume(Integer id, User user, String name, String resumeTitle, String hopeJob, String career, String license, String content, String edu, Integer role, Skill skill) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.resumeTitle = resumeTitle;
        this.hopeJob = hopeJob;
        this.career = career;
        this.license = license;
        this.content = content;
        this.edu = edu;
        this.role = role;
        this.skill = skill;
    }

}