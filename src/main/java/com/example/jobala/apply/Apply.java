package com.example.jobala.apply;

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
@Table(name = "apply_tb")
// user_id, role, resume_id, jobopen_id, created_at
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String state; // 열람전, 합격, 불합격
    private Integer role; // 0 -> guest, 1 -> comp

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    private Jobopen jobopen;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // 기업일수도 있고, 개인일 수도 있다.

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Apply(Integer id, String state, Integer role, Resume resume, Jobopen jobopen, User user) {
        this.id = id;
        this.state = state;
        this.role = role;
        this.resume = resume;
        this.jobopen = jobopen;
        this.user = user;
    }
}