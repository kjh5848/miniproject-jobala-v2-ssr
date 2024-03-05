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
        private String education;
        private String jobTitle;

    }


    public ApplyRequest mapToApplicantProfileDTO(Apply apply, Resume resume, Jobopen jobopen) {
        ApplyRequest applyRequest = new ApplyRequest();

        // Assuming ApplyRequest has a method to set a ProfileDTO
        ApplyRequest.ProfileDTO profileDTO = new ApplyRequest.ProfileDTO();
        profileDTO.setName(resume.getName());
        profileDTO.setResumeTitle(resume.getResumeTitle());
        profileDTO.setEducation(resume.getEdu()); // Assuming 'getEducation()' method exists in Resume
        profileDTO.setJobTitle(jobopen.getJobopenTitle()); // Assuming 'getJobTitle()' method exists in Jobopen

        applyRequest.setProfileDTO(profileDTO); // Assuming 'setProfileDTO()' setter method exists in ApplyRequest


        return applyRequest;
    }

}
