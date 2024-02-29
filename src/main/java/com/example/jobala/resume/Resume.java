package com.example.jobala.resume;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "resume_tb")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
