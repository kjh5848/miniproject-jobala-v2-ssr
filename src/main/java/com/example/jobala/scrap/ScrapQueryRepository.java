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

}
