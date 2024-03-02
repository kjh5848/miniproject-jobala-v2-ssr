package com.example.jobala.resume;

import jakarta.persistence.EntityManager;
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
