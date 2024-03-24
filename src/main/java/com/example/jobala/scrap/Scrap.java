package com.example.jobala.scrap;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Data
@Table(name = "scrap_tb")
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Jobopen jobopen;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Resume resume;

    private Integer role; // 0 -> guest, 1 -> comp
    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Scrap(Integer id, User user, Jobopen jobopen, Resume resume, Integer role, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.jobopen = jobopen;
        this.resume = resume;
        this.role = role;
        this.createdAt = createdAt;
    }
}
