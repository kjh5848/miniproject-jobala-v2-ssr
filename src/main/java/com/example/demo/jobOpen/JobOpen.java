package com.example.demo.jobOpen;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "jobOpen_tb")
public class JobOpen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
