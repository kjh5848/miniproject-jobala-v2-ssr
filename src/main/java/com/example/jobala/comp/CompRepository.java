package com.example.jobala.comp;

import com.example.jobala.apply.ApplyResponse;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompRepository {
    private final EntityManager em;


    public List<CompResponse.ResumeDTO> resumeList(int userId){
        String q = """
            SELECT rt.id, rt.resume_title, rt.career, rt.edu
                   FROM resume_tb rt
                   INNER JOIN apply_tb at ON rt.id = at.resume_id
                   WHERE at.user_id = :userId
           """;

        Query query = em.createNativeQuery(q); // CompResponse.ResumeDTO.class 제거
        query.setParameter("userId", userId);

        // 쿼리 실행 후 결과를 CompResponse.ResumeDTO 리스트로 변환하는 로직 필요
        List<Object[]> resultObjects = query.getResultList();
        List<CompResponse.ResumeDTO> results = new ArrayList<>();
        for (Object[] result : resultObjects) {
            results.add(new CompResponse.ResumeDTO(
                    (Integer) result[0], // id
                    (String) result[1],  // resume_title
                    (String) result[2],  // career
                    (String) result[3]   // edu
            ));
        }

        return results;
    }





//    public List<Resume> findAllResumesByUserId(Integer userId) {
//        String q = "SELECT r FROM Resume r WHERE r.userId = :userId ORDER BY r.id DESC";
//        return em.createQuery(q, Resume.class)
//                .setParameter("userId", userId)
//                .getResultList();
//    }


    public List<Resume> findResumeById(Integer userId) {
        Query query = em.createNativeQuery("select * from resume_tb where user_id = ? order by id desc", Resume.class);
        query.setParameter(1, userId);

        List<Resume> resumeList = query.getResultList();
        return resumeList;
    }

    public List<CompResponse.ScoutListDTO> scoutList(){
        String q = """
                SELECT rt.name, rt.resume_title, ut.age, ut.address, rt.career
                FROM user_tb ut
                INNER JOIN (
                select * 
                from resume_tb
                where id in (
                select max(id)
                from resume_tb
                group by user_id
                )
                ) rt ON ut.id = rt.user_id
                """;

        Query query = em.createNativeQuery(q);

       JpaResultMapper rm = new JpaResultMapper();
       List<CompResponse.ScoutListDTO> results = rm.list(query, CompResponse.ScoutListDTO.class);
        System.out.println(results);

        return results;
    }

    public List<Resume> findResumeAll() {
        String q = """
                select * from resume_tb order by id desc;              
                """;
        Query query = em.createNativeQuery(q, Resume.class);
        return query.getResultList();
    }

    public List<Jobopen> findJobopenAll() {
        String q = """
                select * from jobopen_tb order by id desc;              
                """;
        Query query = em.createNativeQuery(q, Jobopen.class);
        return query.getResultList();
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
