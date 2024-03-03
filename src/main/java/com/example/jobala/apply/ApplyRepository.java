package com.example.jobala.apply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplyRepository {
    private final EntityManager em;

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

    @Transactional
    public List<Apply> findByUserId(Integer userId) {
        String q = """
            select * from apply_tb where user_id = ?
            """;
        Query query = em.createNativeQuery(q, Apply.class);
        query.setParameter(1, userId);

        return query.getResultList();
    }
}


