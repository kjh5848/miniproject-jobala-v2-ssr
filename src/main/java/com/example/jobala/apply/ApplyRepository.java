package com.example.jobala.apply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    public List<Apply> findByJobOpenId(Integer jobOpenId) {
        Query query = em.createNativeQuery("SELECT * FROM apply_tb WHERE jobopen_id = ?1", Apply.class);
        query.setParameter(1, jobOpenId);
        return query.getResultList();
    }

    public ApplyResponse.CardDetailDTO findByIdWithUser(int id) {
        Query query = em.createNativeQuery("SELECT u.address, u.name, r.title, r.edu, a.status " +
                "FROM user_tb AS u " +
                "INNER JOIN resume_tb AS r ON u.id = r.user_id " +
                "INNER JOIN apply_tb AS a ON u.id = a.user_id " +
                "WHERE a.jobopen_id = ?1 " +
                "ORDER BY u.id ASC");
        query.setParameter(1, id);
        Object[] row = (Object[]) query.getSingleResult();

        ApplyResponse.CardDetailDTO cardDetailDTO = new ApplyResponse.CardDetailDTO();
        cardDetailDTO.setAddress((String) row[0]);
        cardDetailDTO.setName((String) row[1]);
        cardDetailDTO.setTitle((String) row[2]);
        cardDetailDTO.setEdu((String) row[3]);
        cardDetailDTO.setState((String) row[4]);

        return cardDetailDTO;
    }

    public List<Integer> findJobOpenIdsByUserId(Integer userId) {
        Query query = em.createNativeQuery("SELECT DISTINCT jobopen_id FROM apply_tb WHERE user_id = ?1");
        query.setParameter(1, userId);
        return query.getResultList();
    }
}


