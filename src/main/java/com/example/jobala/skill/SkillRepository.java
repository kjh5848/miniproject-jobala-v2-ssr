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
    public void save(SkillRequest.CompSkillDTO reqDTO) {
        String a = """
                insert into skill_tb(role, resume_id, jobopen_id, skills) values (?,?,?,?)
                """;

        Query query = em.createNativeQuery(a, Skill.class);
        query.setParameter(1, reqDTO.getResumeId());
        query.setParameter(2, reqDTO.getJobOpenId());
        query.setParameter(3, reqDTO.getSkills());
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
