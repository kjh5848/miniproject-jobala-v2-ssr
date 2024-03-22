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
    private String compNum; // 사업자 번호
    private String password; //패스워드

    @Column(length = 15)
    private String name; // 이름
    private String compname; // 회사명

    @Column(nullable = false)//널 값 허용 안하는 어노테이션
    private String email; //이메일
    private String phone; //폰번호
    private String ceo; // 기업 대표명
    private String address; //주소

    private Integer role; // 0 -> guest, 1 -> comp

    private Timestamp age;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder

    public User(Integer id, String username, String compNum, String password, String name, String compname, String email, String phone, String ceo, String address, Integer role) {
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
        this.role = role;
    }
}
