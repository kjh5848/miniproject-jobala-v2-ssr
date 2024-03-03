package com.example.jobala.skill;

import lombok.Data;

public class SkillRequest {
    @Data
    public static class CompSkillDTO {

        private Integer resumeId;
        private Integer jobOpenId;
        private String[] skills;
    }

    @Data
    public static class SaveDTO {
        private Integer resumeId;
        private Integer jobOpenId;
        private String skills;
    }
}
