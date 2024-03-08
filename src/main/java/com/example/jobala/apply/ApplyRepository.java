package com.example.jobala.apply;

import com.example.jobala._user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplyRepository {

    private final EntityManager em;

    @Transactional
    public void statusUpdate(Integer applyId, String status) {
        Query query = em.createNativeQuery("UPDATE apply_tb SET state = ? WHERE id = ?");
        query.setParameter(1, status);
        query.setParameter(2, applyId);
        query.executeUpdate();
    }

    public List<ApplyResponse.ApplyDTO> findApplyCompByUserId(int compId, String state){ // 로그인한 기업 ID
        String q = """
                SELECT at.id, jot.jobopen_title, rt.resume_title, rt.name, rt.edu, jot.end_Time, at.state, rt.id
                FROM apply_tb at 
                INNER JOIN jobopen_tb jot ON at.jobopen_id = jot.id 
                INNER JOIN resume_tb rt ON rt.id = at.resume_id 
                WHERE jot.user_id = ? and at.role = 0 and at.state = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, compId);
        query.setParameter(2, state);

        // qlrm 사용하기
        JpaResultMapper mapper = new JpaResultMapper();
        List<ApplyResponse.ApplyDTO> responseDTO = mapper.list(query, ApplyResponse.ApplyDTO.class);
        return responseDTO;
    }

    public List<ApplyResponse.ApplyDTO> findByUserId(int userId){ // 로그인한 User ID
        String q = """
                SELECT at.id, jot.jobopen_title, rt.resume_title, rt.name, rt.edu, jot.end_Time, at.state, rt.id
                FROM apply_tb at
                INNER JOIN jobopen_tb jot ON at.jobopen_id = jot.id
                INNER JOIN resume_tb rt ON rt.id = at.resume_id
                WHERE at.user_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, userId);

        // qlrm 사용하기
        JpaResultMapper mapper = new JpaResultMapper();
        List<ApplyResponse.ApplyDTO> responseDTO = mapper.list(query, ApplyResponse.ApplyDTO.class);
        return responseDTO;
    }


    public List<ApplyResponse.ApplyDTO2> findJopOpenByUserId(int userId, String state){ // 로그인한 User ID(제안 받은 공고 확인하는 쿼리)
        String q = """
                SELECT at.id, jot.jobopen_title, rt.resume_title, jot.compname, rt.edu, jot.end_Time, at.state, jot.id
                FROM apply_tb at
                INNER JOIN jobopen_tb jot ON at.jobopen_id = jot.id
                INNER JOIN resume_tb rt ON rt.id = at.resume_id
                WHERE rt.user_id = ? and at.role = 1 and at.state = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, userId);
        query.setParameter(2, state);

        // qlrm 사용하기
        JpaResultMapper mapper = new JpaResultMapper();
        List<ApplyResponse.ApplyDTO2> responseDTO = mapper.list(query, ApplyResponse.ApplyDTO2.class);
        return responseDTO;
    }

    public List<ApplyResponse.HireDTO> hfindAllByUserId(int compId){ // 로그인한 기업 ID
        String q = """
                SELECT at.id, jot.jobopen_title, rt.resume_title, rt.name, at.state
                FROM apply_tb at
                INNER JOIN jobopen_tb jot ON at.jobopen_id = jot.id
                INNER JOIN resume_tb rt ON rt.id = at.resume_id
                WHERE at.user_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, compId);

        // qlrm 사용하기
        JpaResultMapper mapper = new JpaResultMapper();
        List<ApplyResponse.HireDTO> responseDTO2 = mapper.list(query, ApplyResponse.HireDTO.class);
        return responseDTO2;
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
    public void jobopenApplySave(ApplyRequest.JobopenApplyDTO reqDTO, User sessionUser) {
        String q = """
                insert into apply_tb(user_id, role, resume_id, jobopen_id,state, created_at)values (?,?,?,?,?,now())
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, sessionUser.getId());
        query.setParameter(2, sessionUser.getRole());
        query.setParameter(3, reqDTO.getResumeId());
        query.setParameter(4, reqDTO.getJobopenId());
        query.setParameter(5, "검토중");
        query.executeUpdate();
    }

    @Transactional
    public void resumeApplySave(ApplyRequest.ResumeApplyDTO reqDTO, User sessionUser) {
        String q = """
                insert into apply_tb(user_id, role, resume_id, jobopen_id,state, created_at)values (?,?,?,?,?,now())
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, sessionUser.getId());
        query.setParameter(2, sessionUser.getRole());
        query.setParameter(3, reqDTO.getResumeId());
        query.setParameter(4, reqDTO.getJobopenId());
        query.setParameter(5, "검토중");
        query.executeUpdate();
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

    public int countJobopenApplyById(int jobopenId) {
        String q = """
                select count(*) from apply_tb where jobopen_id = ? and role = 0;
                """;
        Query query = em.createNativeQuery(q, Long.class);
        query.setParameter(1,jobopenId);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }
}