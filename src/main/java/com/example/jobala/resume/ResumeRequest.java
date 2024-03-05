package com.example.jobala.resume;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ResumeRequest {

    @AllArgsConstructor
    @Data
    public static class SaveDTO {
        private String resumeTitle;
        private String hopeJob;
        private String career;
        private String license;
        private String content;
        private String edu;
        private List<String> skills = new ArrayList<>();
    }

    @AllArgsConstructor
    @Data
    public static class DeleteDTO {
        private int id;
    }

    @AllArgsConstructor
    @Data
    public static class UpdateDTO {
        private String resumeTitle;
        private String hopeJob;
        private String career;
        private String license;
        private String content;
        private String edu;
    }
}
