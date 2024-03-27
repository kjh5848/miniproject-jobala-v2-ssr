package com.example.jobala.apply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplyQueryRepository {

    private final EntityManager em;

    // 로그인한 User ID(제안 받은 공고 확인하는 쿼리)
    public List<ApplyResponse.GuestPositionDTO> findJopOpenByUserId(int userId) {
        //개인이 제안받은 현황보기
        String q = """
                    SELECT at.id, jot.id, rt.id, jot.jobopen_title, rt.resume_title, ut.name, rt.edu, jot.end_time, at.state
                    FROM apply_tb at
                    join user_tb ut on at.resume_id = ut.id
                    INNER JOIN jobopen_tb jot ON at.jobopen_id = jot.id
                    INNER JOIN resume_tb rt ON rt.id = at.resume_id
                    WHERE rt.user_id = ? and at.role = 1 ;
                    """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, userId);

        // qlrm 사용하기
        JpaResultMapper mapper = new JpaResultMapper();
        List<ApplyResponse.GuestPositionDTO> responseDTO = mapper.list(query, ApplyResponse.GuestPositionDTO.class);
        return responseDTO;
    }


    public List<ApplyResponse.CompPositionDTO> findApplyCompByUserId(int compId) {
        //기업이 제안한 현황보기
        String q = """
                SELECT at.id,jt.id, rt.id, jt.jobopen_title, rt.resume_title, ut.name, rt.edu, jt.end_time, at.state
                FROM apply_tb at
                join user_tb ut on at.resume_id = ut.id
                INNER JOIN jobopen_tb jt ON at.jobopen_id = jt.id
                INNER JOIN resume_tb rt ON rt.id = at.resume_id
                WHERE at.user_id = ? ;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, compId);

        JpaResultMapper mapper = new JpaResultMapper();
        List<ApplyResponse.CompPositionDTO> responseDTO = mapper.list(query, ApplyResponse.CompPositionDTO.class);
        return responseDTO;
    }


    /**
     * role로 개인과 기업을 구분하고
     * 기업이면 공고를 기준으로 지원받은 이력서를 조회
     * 개인이면 어플라이를 기준으로 지원한 이력서를 조회
     */
    //기업 applyForm
    public List<ApplyResponse.CompApplyDTO> findByUserId(int sessionUserId, int role) {
            String q = """
                select at.id, jt.id, rt.id, jt.jobopen_title, rt.resume_title, ut.name, rt.edu, jt.end_time, at.state
                from apply_tb at 
                join user_tb ut on at.resume_id = ut.id
                join jobopen_tb jt ON at.jobopen_id = jt.id
                join resume_tb rt ON rt.id = at.resume_id
                where jt.user_id= ? and at.role= 0 order by id desc;
                    """;
            Query query = em.createNativeQuery(q)
                    .setParameter(1, sessionUserId);

            JpaResultMapper mapper = new JpaResultMapper();
            List<ApplyResponse.CompApplyDTO> responseDTO = mapper.list(query, ApplyResponse.CompApplyDTO.class);
            return responseDTO;
    }

    public List<ApplyResponse.CompApplyDTO> findByUserId2(int sessionUserId, int role) {
        return em.createQuery("""
        SELECT NEW ApplyResponse.CompApplyDTO(at.id, jt.id, r.id, jt.jobopenTitle, r.resumeTitle, ut.name, r.edu, jt.endTime, at.state) 
        FROM Apply at 
        JOIN at.resume resume r 
        JOIN resume.user ut 
        JOIN at.jobOpening jobOpen jt 
        WHERE jobOpen.user.id = :sessionUserId AND at.role = :role 
        ORDER BY at.id DESC
    """, ApplyResponse.CompApplyDTO.class)
                .setParameter("sessionUserId", sessionUserId)
                .setParameter("role", role)
                .getResultList();
    }

    //개인 applyForm
    public List<ApplyResponse.GuestApplyDTO> findByCompUserId(int sessionUserId) {
            //개인이 지원한 이력서 현황보기
            String q = """
                select at.id, rt.id, jt.id, jt.jobopen_title, rt.resume_title, ut.compname, jt.end_time, at.state
                from apply_tb at
                join user_tb ut on at.user_id= ut.id
                join jobopen_tb jt ON at.jobopen_id = jt.id
                join resume_tb rt ON rt.id = at.resume_id
                where at.user_id= ? order by id desc;
                """;
            Query query = em.createNativeQuery(q)
                    .setParameter(1, sessionUserId);

            JpaResultMapper mapper = new JpaResultMapper();
            List<ApplyResponse.GuestApplyDTO> responseDTO = mapper.list(query, ApplyResponse.GuestApplyDTO.class);
            return responseDTO;
    }


}