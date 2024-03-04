package com.example.jobala.apply;

import com.example.jobala.resume.Resume;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplyRepository {

    private final EntityManager em;

    public List<Apply> findByJobOpenId(Integer jobopenId) {
        Query query = em.createNativeQuery("SELECT * FROM apply_tb WHERE jobopen_id = ?1", Apply.class);
        query.setParameter(1, jobopenId);
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
        List<Integer> jobOpenIds = query.getResultList();

        // 사용자가 실제로 지원한 공고만 필터링
        List<Integer> appliedJobOpenIds = new ArrayList<>();
        for (Integer jobOpenId : jobOpenIds) {
            if (userAppliedToJobOpen(userId, jobOpenId)) {
                appliedJobOpenIds.add(jobOpenId);
            }
        }
        return appliedJobOpenIds;
    }

    private boolean userAppliedToJobOpen(Integer userId, Integer jobOpenId) {
        Query query = em.createNativeQuery("SELECT COUNT(*) FROM apply_tb WHERE user_id = ?1 AND jobopen_id = ?2");
        query.setParameter(1, userId);
        query.setParameter(2, jobOpenId);
        int count = ((Number) query.getSingleResult()).intValue();
        return count > 0;
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


