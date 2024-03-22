package com.example.jobala.skill;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@NoArgsConstructor
@Entity
@Data
@Table(name = "skill_tb")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ColumnDefault("1")
    private Integer role; // 0 -> guest, 1 -> comp
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Resume resume; // 개인 null
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Jobopen jobopen; // 1

    private String name; // 스킬 이름

    @Builder
    public Skill(Integer id, Integer role, Resume resume, Jobopen jobopen, String name) {
        this.id = id;
        this.role = role;
        this.resume = resume;
        this.jobopen = jobopen;
        this.name = name;
    }
}
