package com.example.jobala.guest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public class GuestRequest {

    @Getter
    @AllArgsConstructor
    public static class GuestProfileUpdateDTO {
        private String name;
        private String password;
        private String phone;
        private String email;
        private String address;
        private String imgTitle;
        private MultipartFile imgFilename;
    }
}