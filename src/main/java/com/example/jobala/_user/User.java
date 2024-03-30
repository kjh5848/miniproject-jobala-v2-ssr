package com.example.jobala._user;

import com.example.jobala.comp.CompRequest;
import com.example.jobala.guest.GuestRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Timestamp;

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

    private String imgTitle; // 이미지 이름
    private String imgFilename; // 파일 패스

    private Integer role; // 0 -> guest, 1 -> comp

    private Date age;

    @CreationTimestamp
    private Timestamp createdAt;

    // 사진이 null로 들어올때 디폴트 값 설정하기
    @PrePersist // 엔티티가 저장되기 전에 실행되는 메서드, 필드에 기본값 설정
    public void setDefaultImgFilename() {
        if(imgFilename == null) {
            imgFilename = "default.png";
        }
    }

    @Builder
    public User(Integer id, String username, String compNum, String password, String name, String compname, String email, String phone, String ceo, String address, Integer role, Date age, String imgFilename) {
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
        this.age = age;
        this.imgFilename = imgFilename; // 생성자에서 imgFilename을 설정할 수 있도록 추가
    }

    //프로필 업데이트 setter
    public void setGuestProfileUpdateDTO(GuestRequest.GuestProfileUpdateDTO reqDTO, String webImgPath) {
        this.name = reqDTO.getName();
        this.password = reqDTO.getPassword();
        this.phone = reqDTO.getPhone();
        this.email = reqDTO.getEmail();
        this.imgTitle = reqDTO.getImgTitle();
        this.imgFilename = webImgPath;
    }

    public void setCompProfileUpdateDTO(CompRequest.CompProfileUpdateDTO reqDTO, String webImgPath) {
        this.name = reqDTO.getName();
        this.password = reqDTO.getPassword();
        this.phone = reqDTO.getPhone();
        this.email = reqDTO.getEmail();
        this.address = reqDTO.getAddress();
        this.imgTitle = reqDTO.getImgTitle();
        this.imgFilename = webImgPath;
    }
}