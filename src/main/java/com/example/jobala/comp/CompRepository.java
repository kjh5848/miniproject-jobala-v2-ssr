package com.example.jobala.comp;

import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompRepository {
    private final EntityManager em;

    @Transactional
    public void updateProfile(CompResponse.CProfileUpdateDTO profileDto) {
        System.out.println("profileDto = " + profileDto);
        Query query = em.createNativeQuery("UPDATE user_tb SET name = ?, password = ?, phone = ?, email = ?, address = ? WHERE id = ?");
        query.setParameter(1, profileDto.getName());
        query.setParameter(2, profileDto.getPassword());
        query.setParameter(3, profileDto.getPhone());
        query.setParameter(4, profileDto.getEmail());
        query.setParameter(5, profileDto.getAddress());
        query.setParameter(6, profileDto.getId());
        query.executeUpdate();
    }

    public List<CompResponse.CompProfileDTO> findProfileByUserId(int userId) {
        Query query = em.createNativeQuery("SELECT name, password, phone, email, compname, address, comp_num FROM user_tb WHERE id = ?", CompResponse.CompProfileDTO.class);
        query.setParameter(1, userId);

        List<CompResponse.CompProfileDTO> CompProfile = query.getResultList();
        return CompProfile;
    }

    public List<Resume> findAll(String skills, CompResponse.SearchDTO resDTO) {
        String skillQuery = """
                SELECT rt.* FROM resume_tb rt INNER JOIN skill_tb sk ON rt.id = sk.resume_id
                where sk.name like ? 
                AND (rt.career IN (?, ?))
                AND (rt.edu IN (?, ?))
                AND (rt.hope_job IN (?, ?))
                order by rt.id desc
                """;

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

        Query query = em.createNativeQuery(skillQuery, Resume.class);
        query.setParameter(1, "%" + skills + "%");
        query.setParameter(2, career[0]);
        query.setParameter(3, career[1]);
        query.setParameter(4, edu[0]);
        query.setParameter(5, edu[1]);
        query.setParameter(6, hopeJob[0]);
        query.setParameter(7, hopeJob[1]);

        List<Resume> resumeList = query.getResultList();

        return resumeList;
    }

    public List<Resume> findResumeById(int userId) {
        Query query = em.createNativeQuery("select * from resume_tb where user_id = ? order by id desc", Resume.class);
        query.setParameter(1, userId);

        List<Resume> resumeList2 = query.getResultList();
        return resumeList2;
    }

    public List<CompResponse.ScoutListDTO> scoutList() {
        String q = """
                SELECT rt.name, rt.resume_title, ut.age, ut.address, rt.career
                FROM user_tb ut
                INNER JOIN (
                select * 
                from resume_tb
                where id in (
                select max(id)
                from resume_tb
                group by user_id
                )
                ) rt ON ut.id = rt.user_id
                """;

        Query query = em.createNativeQuery(q);

        JpaResultMapper rm = new JpaResultMapper();
        List<CompResponse.ScoutListDTO> results = rm.list(query, CompResponse.ScoutListDTO.class);
        System.out.println(results);

        return results;
    }

    public List<Resume> findResumeAll() {
        String q = """
                select * from resume_tb order by id desc;              
                """;
        Query query = em.createNativeQuery(q, Resume.class);
        return query.getResultList();
    }

    public List<Jobopen> findJobopenById(int id) {
        String q = """
                select * from jobopen_tb where user_id= ? order by id desc;
                """;
        Query query = em.createNativeQuery(q, Jobopen.class);
        query.setParameter(1, id);
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
}