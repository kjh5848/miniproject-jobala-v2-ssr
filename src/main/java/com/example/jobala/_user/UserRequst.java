package com.example.jobala._user;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

public class UserRequst {
    @Data
    public static class joinDTO {
        private Integer id;

        private String username; // 아아디

        private String email;
        private String compNum;
        private String password;
        private String name;
        private String compname;
        private String phone;
        private String ceo;
        private String address;
        private Integer role; // 0 -> guest, 1 -> comp
        private String photo;
        private LocalDateTime createdAt;


    }
}
