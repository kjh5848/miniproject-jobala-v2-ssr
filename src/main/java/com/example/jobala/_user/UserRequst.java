package com.example.jobala._user;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

public class UserRequst {
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
        private String age;
        private String phone;
        private Integer role; // 0 -> guest, 1 -> comp
        private LocalDateTime createdAt;
    }

    @Data
    public static class loginDTO {
        private String username;
        private String password;
        private Integer role;
    }
}
