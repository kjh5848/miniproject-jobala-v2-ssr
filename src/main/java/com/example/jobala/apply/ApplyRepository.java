package com.example.jobala.apply;

import com.example.jobala.resume.Resume;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplyRepository {
    @Autowired
    private final EntityManager em;


    /**
     * select at.id, jot.jobopen_title, rt.resume_title, rt.name
     * from apply_tb at inner join jobopen_tb jot on at.jobopen_id = jot.id
     * inner join resume_tb rt on rt.id = at.resume_id
     * where at.user_id = 3;
     */

    public List<ApplyResponse.ApplyDTO> findAllByUserId(int compId){ // 로그인한 기업 ID
        String q = """
                select at.id, jot.jobopen_title, rt.resume_title, rt.name 
                from apply_tb at inner join jobopen_tb jot on at.jobopen_id = jot.id 
                inner join resume_tb rt on rt.id = at.resume_id 
                where at.user_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, compId);

        // qlrm 사용하기
        JpaResultMapper mapper = new JpaResultMapper();
        List<ApplyResponse.ApplyDTO> responseDTO = mapper.list(query, ApplyResponse.ApplyDTO.class);
        return responseDTO;
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

    @Transactional
    public Apply findById(Integer id) {
        Query query = em.createNativeQuery("select * from apply_tb where id = ?", Apply.class);
        query.setParameter(1, id);

        try {
            Apply apply = (Apply) query.getSingleResult();
            return apply;
        } catch (Exception e) {
            return null;
        }
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


