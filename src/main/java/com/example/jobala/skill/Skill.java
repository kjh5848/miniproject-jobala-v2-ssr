package com.example.jobala.skill;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Data
@Table(name = "skill_tb")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId; // 세션 값

    @ColumnDefault("1")
    private Integer role; // 0 -> guest, 1 -> comp
    private Integer resumeId; // 개인 null
    private Integer jobopenId; // 1
    private String name; // 스킬 이름
}
