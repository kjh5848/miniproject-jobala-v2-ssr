package com.example.jobala.scrap;

import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScrapQueryRepository {
    private final EntityManager em;

    public List<Resume> findResumeAll(Integer userId) {
        String q = """
                SELECT r.* FROM resume_tb r inner join Scrap_tb s on r.id = s.resume_id 
                where s.user_id = ? ORDER BY r.id DESC;
                """;
        Query query = em.createNativeQuery(q, Resume.class);
        query.setParameter(1, userId);
        return query.getResultList();
    }


    public Scrap findCompScrapById(Integer resumeId, Integer userId) {
        String q = """
                select * from scrap_tb where resume_id = ? AND user_id = ?; 
                """;
        Query query = em.createNativeQuery(q, Scrap.class);
        query.setParameter(1, resumeId);
        query.setParameter(2, userId);
        Scrap scrap = (Scrap) query.getSingleResult();
        return scrap;
    }

    @Transactional
    public void compScrapSave(Integer resumeId, Integer userId) {
        String q = """
                insert into scrap_tb(user_id, resume_id, role, created_at) values (?,?,?,now());
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, userId);
        query.setParameter(2, resumeId);
        query.setParameter(3, 1);
        query.executeUpdate();
    }

    @Transactional
    public void compScrapDelete(Integer id) {
        String q = """
                delete from scrap_tb where id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);
        query.executeUpdate();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Jobopen> findJobopenAll(Integer userId) {
        String q = """
                SELECT r.* FROM jobopen_tb r inner join scrap_tb s on r.id = s.jobopen_id
                where s.user_id = ? ORDER BY r.id DESC;
                """;
        Query query = em.createNativeQuery(q, Jobopen.class);
        query.setParameter(1, userId);
        return query.getResultList();
    }


    public Scrap findGuestScrapById(Integer jobopenId, Integer userId) {
        String q = """
                select * from scrap_tb where role = 0 AND jobopen_id = ? AND user_id = ?; 
                """;
        Query query = em.createNativeQuery(q, Scrap.class);
        query.setParameter(1, jobopenId);
        query.setParameter(2, userId);
        Scrap scrap = (Scrap) query.getSingleResult();
        return scrap;
    }

    @Transactional
    public void guestScrapSave(Integer jobopenId, Integer userId) {
        String q = """
                insert into scrap_tb(user_id, jobopen_id, role, created_at) values (?,?,?,now());
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, userId);
        query.setParameter(2, jobopenId);
        query.setParameter(3, 0);
        query.executeUpdate();
    }

    @Transactional
    public void guestScrapDelete(Integer id) {
        String q = """
                delete from scrap_tb where id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);
        query.executeUpdate();
    }

    public void findAll() {
        return;
    }


    @Transactional
    public void update() {
        return;
    }
}
