package com.example.jobala.skill;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class SkillRepository {
    private final EntityManager em;

    public void findAll() {
        return;
    }

    public void findById() {
        return;
    }

    @Transactional
    public void save() {

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
