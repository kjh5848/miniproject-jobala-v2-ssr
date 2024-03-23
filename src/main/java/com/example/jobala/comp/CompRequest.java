package com.example.jobala.comp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public class CompRequest {


    // FIX: 파스칼 표기법 필요
    // DEL: Response에서 ScoutListDTO 만듬
    @Getter
    @AllArgsConstructor
    public static class CompProfileUpdateDTO {
        private String name;
        private String password;
        private String phone;
        private String email;
        private String address;
        private String imgTitle;
        private MultipartFile imgFilename;
    }
}
