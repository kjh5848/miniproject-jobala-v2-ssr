package com.example.jobala.scrap;

import com.example.jobala._user.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "scrap_tb")
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private Integer jobopenId;
    private Integer resumeId;
    private Integer role; // 0 -> guest, 1 -> comp
    @CreationTimestamp
    private Timestamp createdAt;
}
