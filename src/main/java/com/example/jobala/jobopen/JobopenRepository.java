package com.example.jobala.jobopen;

import com.example.jobala.skill.Skill;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class JobopenRepository {
    private final EntityManager em;

    public void findAll() {
        return;
    }

    public Jobopen findById(int id) {
        String a = """
                select * from jobopen_tb where id =?
                """;

        Query query = em.createNativeQuery(a);
        query.setParameter(1, id);
        Jobopen jobopen = (Jobopen) query.getSingleResult();
        return jobopen;
    }

    @Transactional
    public void save(JobopenRequest.WriteDTO reqDTO, int userId, Integer role) {

        System.out.println("reqDTO = " + reqDTO);
        String a = """
                insert into jobopen_tb(user_id,edu, career, job_type, salary, hope_job ,comp_location ,content , end_time , jobopen_title,created_at,role, skills) values (?,?,?,?,?,?,?,?,?,?,now(),?,?)
                """;
        Query query = em.createNativeQuery(a, Jobopen.class);
        query.setParameter(1, userId);
        query.setParameter(2, reqDTO.getEdu());
        query.setParameter(3, reqDTO.getCareer());
        query.setParameter(4, reqDTO.getJobType());
        query.setParameter(5, reqDTO.getSalary());
        query.setParameter(6, reqDTO.getHopeJob());
        query.setParameter(7, reqDTO.getCompLocation());
        query.setParameter(8, reqDTO.getContent());
        query.setParameter(9, reqDTO.getEndTime());
        query.setParameter(10, reqDTO.getJobopenTitle());
        query.setParameter(11, role);
        query.setParameter(12, reqDTO.getSkills());

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

    @Transactional
    public void skillSave(JobopenRequest.SkillDTO skillDTO, Integer id, Integer role) {
        String a = """
                insert into skill_tb(jobopen_id, skills,role) values (?,?,?)
                """;
        Query query = em.createNativeQuery(a, Skill.class);
        query.setParameter(1, id);
        query.setParameter(2, skillDTO.getSkills());
        query.setParameter(3, role);
        query.executeUpdate();
    }

    public Jobopen findByIdWithUser(int id) {
        String a = """
                select * from jobopen_tb where id =?
                """;

        Query query = em.createNativeQuery(a);
        query.setParameter(1, id);
        Jobopen jobopen = (Jobopen) query.getSingleResult();
        return jobopen;
    }


}
