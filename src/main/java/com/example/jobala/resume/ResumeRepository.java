package com.example.jobala.resume;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ResumeRepository {
    private final EntityManager em;

    public Resume findByUserId(Integer userId) {
        String q = """
            SELECT r FROM Resume r WHERE r.id = (
                SELECT a.resumeId FROM Apply a WHERE a.userId = :userId
            )
            """;
        return em.createQuery(q, Resume.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    public void findAll() {
        return;
    }

    public void findById() {
        return;
    }

    @Transactional
    public void save(ResumeRequest.SaveDTO resumeSaveDTO, int userId) {
        Query query = entityManager.createNativeQuery("insert into resume_tb(user_id, resume_title, hope_job, career, license, content, edu, created_at) values (?,?,?,?,?,?,?,now())");
        System.out.println(resumeSaveDTO);
        query.setParameter(1, userId);
        query.setParameter(2, resumeSaveDTO.getResumeTitle());
        query.setParameter(3, resumeSaveDTO.getHopeJob());
        query.setParameter(4, resumeSaveDTO.getCareer());
        query.setParameter(5, resumeSaveDTO.getLicense());
        query.setParameter(6, resumeSaveDTO.getContent());
        query.setParameter(7, resumeSaveDTO.getEdu());

        query.executeUpdate();
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
