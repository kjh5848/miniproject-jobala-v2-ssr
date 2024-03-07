package com.example.jobala.scrap;

import com.example.jobala.resume.Resume;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScrapRepository {
    private final EntityManager em;

    public List<Resume> findResumeAll() {
        String q = """
                select * from resume_tb order by id desc;              
                """;
        Query query = em.createNativeQuery(q, Resume.class);
        return query.getResultList();
    }

    public void findAll() {
        return;
    }

    public Scrap findCompScrapById(int resumeId, int userId) {
        String q = """
                select * from scrap_tb where role = 1 AND resume_id = ? AND user_id = ?; 
                """;
        Query query = em.createNativeQuery(q, Scrap.class);
        query.setParameter(1, resumeId);
        query.setParameter(2, userId);
        Scrap scrap = (Scrap) query.getSingleResult();
        return scrap;
    }

    public int findCompScrapByIds(int resumeId, int userId) {
        String q = """
                select count(*) from scrap_tb where role = 1 AND resume_id = ? AND user_id = ?; 
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, resumeId);
        query.setParameter(2, userId);
        return ((Number) query.getSingleResult()).intValue();
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

    @Transactional
    public void compScrapSave(int resumeId, int userId) {
        String q = """
                insert into scrap_tb(user_id, resume_id, role, create_at) values (?,?,?,now());
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, userId);
        query.setParameter(2, resumeId);
        query.setParameter(3, 1);
        query.executeUpdate();
    }

    @Transactional
    public void compScrapDelete(int id) {
        String q = """
                delete from scrap_tb where id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1,id);
        query.executeUpdate();
    }
}
