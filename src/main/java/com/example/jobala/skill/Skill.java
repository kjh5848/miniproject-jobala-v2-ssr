package com.example.jobala.skill;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "skill_tb")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer role; // 0 -> guest, 1 -> comp
    private Integer resumeId;
    private Integer jobopenId;
    private String skills;
}
