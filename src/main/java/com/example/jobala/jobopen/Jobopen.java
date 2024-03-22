package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala.skill.Skill;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "jobopen_tb")
public class Jobopen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private String jobopenTitle; //공고제목
    private String content; //내용
    private String career;// 경력
    private String edu; // 학력
    private String hopeJob; //희망직종
    private String compLocation; //근무지역
    private String jobType; // 고용형태
    private String salary; //연봉
    private Date endTime; // 마감일

    @CreationTimestamp
    private Timestamp createdAt; //생성일

    @ColumnDefault("1")
    private Integer role; // 역할 0 -> guest, 1 -> comp

    @OneToOne(mappedBy = "jobopen",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Skill skill;

    // findByIdWithUser_test에서 무한 참조 해결용
    @Override
    public String toString() {
        return "Jobopen{" +
                "id=" + id +
                ", jobopenTitle='" + jobopenTitle + '\'' +
                ", content='" + content + '\'' +
                ", career='" + career + '\'' +
                ", edu='" + edu + '\'' +
                ", hopeJob='" + hopeJob + '\'' +
                ", compLocation='" + compLocation + '\'' +
                ", jobType='" + jobType + '\'' +
                ", salary='" + salary + '\'' +
                ", endTime=" + endTime +
                ", createdAt=" + (createdAt != null ? createdAt.toString() : null) +
                ", role=" + role +
                // ", skill=" + skill + // Skill 객체는 출력하지 않음
                '}';
    }
//    @Transient
//    private Integer count;
}