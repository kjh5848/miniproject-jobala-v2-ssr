package com.example.jobala._user;

import com.example.jobala.Pic.Pic;
import com.example.jobala.apply.Apply;
import com.example.jobala.board.Board;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import com.example.jobala.scrap.Scrap;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Data
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 15)
    private String username; // 아이디
    private String compNum; //
    private String password;

    @Column(length = 15)
    private String name;
    private String compname;

    @Column(nullable = false)//널 값 허용 안하는 어노테이션
    private String email;
    private String phone;
    private String ceo;
    private String address;


    private Integer role; // 0 -> guest, 1 -> comp

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date age;

    @CreationTimestamp
    private Timestamp createdAt;


    @Builder
    public User(Integer id, String username, String compNum, String password, String name, String compname, String email, String phone, String ceo, String address, Date age, Integer role) {
        this.id = id;
        this.username = username;
        this.compNum = compNum;
        this.password = password;
        this.name = name;
        this.compname = compname;
        this.email = email;
        this.phone = phone;
        this.ceo = ceo;
        this.address = address;
        this.age = age;
        this.role = role;
    }
}
