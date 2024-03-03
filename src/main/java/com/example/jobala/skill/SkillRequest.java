package com.example.jobala.skill;

import lombok.Data;

public class SkillRequest {


    @Data
    public static class CompSkillDTO {
        private Integer role; // 0 -> guest, 1 -> comp
        private Integer resumeId;
        private Integer jobOpenId;
        private String[] skills;
    }
}
