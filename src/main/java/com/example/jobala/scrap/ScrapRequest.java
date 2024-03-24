package com.example.jobala.scrap;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;

public class ScrapRequest {

    // 회사가 이력서를 스크랩
    @AllArgsConstructor
    @Data
    public static class CompScrapDTO {
        private int resumeId;

        public Scrap toEntity(Resume resume, User sessionUser) {
            return Scrap.builder()
                    .user(sessionUser)
                    .jobopen(null)
                    .resume(resume)
                    .role(sessionUser.getRole())
                    .build();
        }
    }


    // 개인이 공고를 스크랩
    @AllArgsConstructor
    @Data
    public static class GuestScrap {
        private Integer jobopenId;
        public Scrap toEntity(Jobopen jobopen, User sessionUser) {
            return Scrap.builder()
                    .user(sessionUser)
                    .jobopen(jobopen)
                    .resume(null)
                    .role(sessionUser.getRole())
                    .build();
        }
    }
}
