package com.example.jobala._user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserRequest {

    @Data
    public static class joinDTO {
        private String compNum;
        private String ceo;
        private String compname;
        private String address;
        private String username;
        private String email;
        private String password;
        private String name;
        private String phone;
        private Date age;
        private Integer role; // 0 -> guest, 1 -> comp

        //개인이 회원가입할때
        public User toGuestEntity() {
            return User.builder()
                    .address(address)
                    .username(username)
                    .email(email)
                    .password(password)
                    .name(name)
                    .phone(phone)
                    .age(age)
                    .role(role)
                    .build();
        }

        //기업이 회원가입할때
        public User toCompEntity() {
            return User.builder()
                    .compname(compname)
                    .ceo(ceo)
                    .compNum(compNum)
                    .address(address)
                    .username(username)
                    .email(email)
                    .password(password)
                    .name(name)
                    .phone(phone)
                    .age(age)
                    .role(role)
                    .build();
        }
    }

    @AllArgsConstructor
    @Data
    public static class loginDTO {
        private String username;
        private String password;

    }
}
