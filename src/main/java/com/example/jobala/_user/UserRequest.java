package com.example.jobala._user;

import lombok.AllArgsConstructor;
import lombok.Data;

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
        private Integer role; // 0 -> guest, 1 -> comp

        public User toEntity() {
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
