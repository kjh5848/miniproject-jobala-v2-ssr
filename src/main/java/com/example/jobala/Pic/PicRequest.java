package com.example.jobala.Pic;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class PicRequest {

    @Data
    public static class UploadDTO {
        private String title;
        private MultipartFile imgFile;
    }

    @Data
    public static class UpdateDTO {
        private String title;
        private MultipartFile imgFile;
    }
}