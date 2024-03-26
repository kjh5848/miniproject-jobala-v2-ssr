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

    public List<ApplyResponse.ApplyDTO2> findJopOpenByUserId(int userId) { // 로그인한 User ID(제안 받은 공고 확인하는 쿼리)
        String q = """
                SELECT at.id, jot.jobopen_title, rt.resume_title, jot.compname, rt.edu, jot.end_Time, at.state, jot.id
                FROM apply_tb at
                INNER JOIN jobopen_tb jot ON at.jobopen_id = jot.id
                INNER JOIN resume_tb rt ON rt.id = at.resume_id
                WHERE rt.user_id = ? and at.role = 1 and at.state = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, userId);
        query.setParameter(2, "검토중");

        // qlrm 사용하기
        JpaResultMapper mapper = new JpaResultMapper();
        List<ApplyResponse.ApplyDTO2> responseDTO = mapper.list(query, ApplyResponse.ApplyDTO2.class);
        return responseDTO;
    }


    public List<ApplyResponse.ApplyDTO> findApplyCompByUserId(int compId) {
        String q = """
                SELECT at.id, jot.jobopen_title, rt.resume_title, rt.name, rt.edu, jot.end_time, at.state, rt.id AS resumeId
                FROM apply_tb at
                INNER JOIN jobopen_tb jot ON at.jobopen_id = jot.id
                INNER JOIN resume_tb rt ON rt.id = at.resume_id
                WHERE jot.user_id = ? AND at.role = 0;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, compId);


        JpaResultMapper mapper = new JpaResultMapper();
        List<ApplyResponse.ApplyDTO> responseDTO = mapper.list(query, ApplyResponse.ApplyDTO.class);
        return responseDTO;
    }

    /**
     * role로 개인과 기업을 구분하고
     * 기업이면 공고를 기준으로 지원받은 이력서를 조회
     * 개인이면 어플라이를 기준으로 지원한 이력서를 조회
     */
    public List<ApplyResponse.ApplyDTO> findByUserId(int sessionUserId, int role) {
        if (role == 1) {
            String q = """
                select at.id, jt.jobopen_title, rt.resume_title, ut.name, rt.edu, jt.end_Time, at.state, at.resume_id
                from apply_tb at 
                join user_tb ut on at.resume_id = ut.id
                join jobopen_tb jt ON at.jobopen_id = jt.id
                join resume_tb rt ON rt.id = at.resume_id
                where jt.user_id= ? and at.role= 0 order by id desc;
                    """;
            Query query = em.createNativeQuery(q)
                    .setParameter(1, sessionUserId);

            JpaResultMapper mapper = new JpaResultMapper();
            List<ApplyResponse.ApplyDTO> responseDTO = mapper.list(query, ApplyResponse.ApplyDTO.class);
            return responseDTO;
        } else {
            String q2 = """
                select at.id, jt.jobopen_title, rt.resume_title, ut.name, rt.edu, jt.end_Time, at.state,  at.resume_id
                from apply_tb at 
                join user_tb ut on at.resume_id = ut.id
                join jobopen_tb jt ON at.jobopen_id = jt.id
                join resume_tb rt ON rt.id = at.resume_id
                where at.user_id= ? order by id desc;
                """;
            Query query2 = em.createNativeQuery(q2)
                    .setParameter(1, sessionUserId);

            JpaResultMapper mapper = new JpaResultMapper();
            List<ApplyResponse.ApplyDTO> responseDTO = mapper.list(query2, ApplyResponse.ApplyDTO.class);
            return responseDTO;
        }

    }


}