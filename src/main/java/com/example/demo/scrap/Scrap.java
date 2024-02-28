package com.example.demo.scrap;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "scrap_tb")
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
