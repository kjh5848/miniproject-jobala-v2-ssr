package com.example.jobala.skill;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "skill_tb")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int resumeId;
    private int jobOpenId;
    private Boolean java;
    private Boolean Javascript;
    private Boolean spring;
    private Boolean html;
    private Boolean jquery;
    private Boolean mysql;
}
