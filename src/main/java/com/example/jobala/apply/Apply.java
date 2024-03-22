package com.example.jobala.apply;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Data
@Table(name = "apply_tb")
// user_id, role, resume_id, jobopen_id, created_at
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    private Jobopen jobopen;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // 기업일수도 있고, 개인일 수도 있다.
    private String state; // 열람전, 합격, 불합격
    private Integer role; // 0 -> guest, 1 -> comp

    @CreationTimestamp
    private Timestamp createdAt;

}