package com.example.jobala.apply;

import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

public class ApplyRequest {

    private ProfileDTO profileDTO;

    public void setProfileDTO(ProfileDTO profileDTO) {
        this.profileDTO = profileDTO;
    }
    @AllArgsConstructor
    @Data
    public static class UpdateDTO {

        private Integer jobopenId; // 채용공고 ID

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class ProfileDTO {

        private String name;
        private String resumeTitle;
        private String edu;
        private String jobTitle;

    }




}
