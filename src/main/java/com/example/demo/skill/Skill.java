package com.example.demo.skill;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "skill_tb")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
