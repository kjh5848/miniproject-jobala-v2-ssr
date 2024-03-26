package com.example.jobala.comp;

import com.example.jobala.guest.GuestResponse;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompQueryRepository {
    private final EntityManager em;

    public List<ResumeResponse.ListDTO> findAll(String skills, CompResponse.SearchDTO resDTO) {
        String skillQuery = """
                SELECT rt.id, rt.name, rt.resume_title, rt.edu, rt.career, 
                (select img_filename from _tb where resume_id =  rt.id) img_filename 
                FROM resume_tb rt INNER JOIN skill_tb sk ON rt.id = sk.resume_id 
                where (sk.name Like ? AND sk.name LIKE ? AND sk.name LIKE ? AND sk.name LIKE ? AND sk.name LIKE ? AND sk.name LIKE ?) 
                AND (rt.career IN (?, ?)) 
                AND (rt.edu IN (?, ?)) 
                AND (rt.hope_job IN (?, ?)) 
                order by rt.id desc 
                """;
        // skill 파싱
        String[] skill = {"", "", "", "", "", ""};
        String[] skillArr;
        try {
            skillArr = skills.split(",");
            for (int i = 0; i < skillArr.length; i++) {
                skill[i] = skillArr[i];
            }
        } catch (Exception e) {
        }

        // career 파싱
        String[] career = {null, null};
        String[] careerArr;
        try {
            careerArr = resDTO.getCareer().split(",");
            for (int i = 0; i < careerArr.length; i++) {
                career[i] = careerArr[i];
            }
        } catch (Exception e) {
            career[0] = "신입";
            career[1] = "경력";
        }

        // edu 파싱
        String[] edu = {null, null};
        String[] eduArr;
        try {
            eduArr = resDTO.getEdu().split(",");
            for (int i = 0; i < eduArr.length; i++) {
                edu[i] = eduArr[i];
            }
        } catch (Exception e) {
            edu[0] = "고등학교 졸업";
            edu[1] = "대학교 졸업";
        }

        // hopeJob 파싱
        String[] hopeJob = {null, null};
        String[] hopeJobArr;
        try {
            hopeJobArr = resDTO.getHopeJob().split(",");
            for (int i = 0; i < hopeJobArr.length; i++) {
                hopeJob[i] = hopeJobArr[i];
            }
        } catch (Exception e) {
            hopeJob[0] = "프론트엔드";
            hopeJob[1] = "백엔드";
        }

        Query query = em.createNativeQuery(skillQuery);
        query.setParameter(1, "%" + skill[0] + "%");
        query.setParameter(2, "%" + skill[1] + "%");
        query.setParameter(3, "%" + skill[2] + "%");
        query.setParameter(4, "%" + skill[3] + "%");
        query.setParameter(5, "%" + skill[4] + "%");
        query.setParameter(6, "%" + skill[5] + "%");
        query.setParameter(7, career[0]);
        query.setParameter(8, career[1]);
        query.setParameter(9, edu[0]);
        query.setParameter(10, edu[1]);
        query.setParameter(11, hopeJob[0]);
        query.setParameter(12, hopeJob[1]);

        JpaResultMapper rm = new JpaResultMapper();
        List<ResumeResponse.ListDTO> resumeList = rm.list(query, ResumeResponse.ListDTO.class);

        return resumeList;
    }

    public List<Resume> findResumeById(int userId) {
        Query query = em.createNativeQuery("select * from resume_tb where user_id = ? order by id desc", Resume.class);
        query.setParameter(1, userId);

        List<Resume> resumeList2 = query.getResultList();
        return resumeList2;
    }

    public List<ResumeResponse.ListDTO> findResumeAll() {
        String q = """
                select rt.id, rt.name, rt.resume_title, rt.edu, rt.career, 
                (select img_filename from user_tb where id =  rt.id) img_filename from resume_tb rt order by id desc;              
                """;
        Query query = em.createNativeQuery(q);
        JpaResultMapper rm = new JpaResultMapper();
        List<ResumeResponse.ListDTO> resumeList = rm.list(query, ResumeResponse.ListDTO.class);
        return resumeList;
    }

    public List<Jobopen> findJobopenById(int id) {
        String q = """
                select * from jobopen_tb where user_id= ? order by id desc;
                """;
        Query query = em.createNativeQuery(q, Jobopen.class);
        query.setParameter(1, id);
        return query.getResultList();
    }
}