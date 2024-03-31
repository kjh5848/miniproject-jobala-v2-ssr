package com.example.jobala._user;


import lombok.Data;

public class UserResponse {

    @Data
    public static class LoginResponseDTO {
        private User user;
        private Boolean isCheck;

        public LoginResponseDTO(User user, Boolean isCheck) {
            this.user = user;
            this.isCheck = isCheck;
        }
    }
}