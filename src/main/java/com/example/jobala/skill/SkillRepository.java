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

    public Skill findByResumeId(Integer id) {
        String q = """
                select * from skill_tb where resume_id = ?;
                """;
        Query query = em.createNativeQuery(q, Skill.class);
        query.setParameter(1,id);

        try {
            Skill skill = (Skill) query.getSingleResult();
            return skill;
        } catch (Exception e) {
            return  null;
        }
    }

    public Skill findByJobopenId(Integer id) {
        String q = """
                select * from skill_tb where jobopen_id = ?;
                """;
        Query query = em.createNativeQuery(q, Skill.class);
        query.setParameter(1,id);

        try {
            Skill skill = (Skill) query.getSingleResult();
            return skill;
        } catch (Exception e) {
            return  null;
        }
    }

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
