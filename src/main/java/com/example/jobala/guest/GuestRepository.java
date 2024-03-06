package com.example.jobala.guest;

import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.Resume;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GuestRepository {
    private final EntityManager em;

    public List<Resume> findResumeById(int userId) {
        Query query = em.createNativeQuery("select * from resume_tb where user_id = ? order by id desc", Resume.class);
        query.setParameter(1, userId);

        List<Resume> resumeList = query.getResultList();
        return resumeList;
    }



    // 모든 필터를 선택해야 한다는 치명적 단점이 존재함
    public List<Jobopen> findAll(String skills, GuestResponse.SearchDTO resDTO) {
        String skillQuery = """
               SELECT jb.* FROM jobopen_tb jb INNER JOIN skill_tb sk ON jb.id = sk.jobopen_id
               where sk.name like ? AND (jb.career IN (?, ?))
               AND (jb.comp_location IN (?, ?, ?, ?))
               AND (jb.edu IN (?, ?))
               AND (jb.salary IN (?, ?, ?))
               AND (jb.hope_job IN (?, ?))
               AND (jb.job_type IN (?, ?, ?))
               order by jb.id desc
               """;

        // career 파싱
        String[] career = {null , null};
        String[] careerArr = new String[0];
        try {
            careerArr = resDTO.getCareer().split(",");
            for (int i = 0; i < careerArr.length ; i++) {
                career[i] = careerArr[i];
            }
        } catch (Exception e) {

        }

        // compLocation 파싱
        String[] compLocation = {null, null, null, null};
        String[] compLocationArr = new String[0];
        try {
            compLocationArr = resDTO.getCompLocation().split(",");
            for (int i = 0; i < compLocationArr.length ; i++) {
                compLocation[i] = compLocationArr[i];
            }
        } catch (Exception e) {

        }

        // edu 파싱
        String[] edu = {null, null};
        String[] eduArr = new String[0];
        try {
            eduArr = resDTO.getEdu().split(",");
            for (int i = 0; i < eduArr.length ; i++) {
                edu[i] = eduArr[i];
            }
        } catch (Exception e) {

        }

        // salary 파싱
        String[] salary = {null, null, null};
        String[] salaryArr = new String[0];
        try {
            salaryArr = resDTO.getSalary().split(",");
            for (int i = 0; i < salaryArr.length ; i++) {
                salary[i] = salaryArr[i];
            }
        } catch (Exception e) {

        }

        // hopeJob 파싱
        String[] hopeJob = {null, null};
        String[] hopeJobArr = new String[0];
        try {
            hopeJobArr = resDTO.getHopeJob().split(",");
            for (int i = 0; i < hopeJobArr.length ; i++) {
                hopeJob[i] = hopeJobArr[i];
            }
        } catch (Exception e) {

        }

        // jobType 파싱
        String[] jobType = {null, null, null};
        String[] jobTypeArr = new String[0];
        try {
            jobTypeArr = resDTO.getJobType().split(",");
            for (int i = 0; i < jobTypeArr.length ; i++) {
                jobType[i] = jobTypeArr[i];
            }
        } catch (Exception e) {

        }

        Query query = em.createNativeQuery(skillQuery, Jobopen.class);
        query.setParameter(1, "%" + skills + "%");
        query.setParameter(2, career[0]);
        query.setParameter(3, career[1]);
        query.setParameter(4, compLocation[0]);
        query.setParameter(5, compLocation[1]);
        query.setParameter(6, compLocation[2]);
        query.setParameter(7, compLocation[3]);
        query.setParameter(8, edu[0]);
        query.setParameter(9, edu[1]);
        query.setParameter(10, salary[0]);
        query.setParameter(11, salary[1]);
        query.setParameter(12, salary[2]);
        query.setParameter(13, hopeJob[0]);
        query.setParameter(14, hopeJob[1]);
        query.setParameter(15, jobType[0]);
        query.setParameter(16, jobType[1]);
        query.setParameter(17, jobType[2]);

        List<Jobopen> jobopenList = query.getResultList();

        return jobopenList;
    }

    public List<Jobopen> findByJoboopenAll() {
        String q = """
                select * from jobopen_tb order by id desc
                """;
        Query query = em.createNativeQuery(q, Jobopen.class);
        return query.getResultList();
    }

    public void findById() {
        return;
    }

    @Transactional
    public void save() {
        return;
    }

    @Transactional
    public void upDate() {
        return;
    }

    @Transactional
    public void delete() {
        return;
    }

    public Jobopen findByIdWithUser(int id) {
        String a = """
                select * from jobopen_tb where id =?
                """;

        Query query = em.createNativeQuery(a, Jobopen.class);
        query.setParameter(1, id);

        try {
            Jobopen jobopen = (Jobopen) query.getSingleResult();
            return jobopen;
        } catch (Exception e) {
            return null;
        }
    }
}
