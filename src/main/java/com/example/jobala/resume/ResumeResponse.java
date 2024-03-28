package com.example.jobala.resume;

import com.example.jobala._user.User;
import com.example.jobala.scrap.Scrap;
import com.example.jobala.scrap.ScrapJPARepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.Optional;


public class ResumeResponse {

    @AllArgsConstructor
    @Data
    public static class ScrapDTO{
        private int id;
        private String name;
        private String resumeTitle;
        private String career;
        private String edu;

        public ScrapDTO(Resume resume) {
            this.id = resume.getId();
            this.name = resume.getUser().getName();
            this.resumeTitle = resume.getResumeTitle();
            this.career = resume.getCareer();
            this.edu = resume.getEdu();
        }
    }

    @AllArgsConstructor
    @Data
    public static class ListDTO {
        private int id;
        private String name;
        private String resumeTitle;
        private String edu;
        private String career;
        private String imgFilename;
    }

    @AllArgsConstructor
    @Data
    public static class DetailDTO {
        private Integer id;
        private String resumeTitle;
        private String hopeJob;
        private String career;
        private String license;
        private String content;
        private String edu;
        private String skills;
        private boolean isScrap;
        private boolean isGuestScrap;
        private UserDTO userDTO;



        public DetailDTO(Resume resume, User sessionUser) {
            this.id = resume.getId();
            this.resumeTitle = resume.getResumeTitle();
            this.hopeJob = resume.getHopeJob();
            this.career = resume.getCareer();
            this.license = resume.getLicense();
            this.content = resume.getContent();
            this.edu = resume.getEdu();
            this.skills = resume.getSkills();
            this.isGuestScrap = false;
            this.isScrap = false;

            this.userDTO = new UserDTO(resume.getUser());

            // 회사만 이력서를 스크랩 할 수 있다.
            if (sessionUser != null) {
                if (sessionUser.getRole() == 1) {
                    this.isGuestScrap = true;
                }
            }
        }

        @Data
        public class UserDTO {
            private Integer userId;
            private String userimgFilename;
            private String name;
            private Date age;
            private String email;
            private String address;

            public UserDTO(User user) {
                this.userId = user.getId();
                this.userimgFilename = user.getImgFilename();
                this.name = user.getName();
                this.age = user.getAge();
                this.email = user.getEmail();
                this.address = user.getAddress();
            }
        }
    }
}
