package com.example.jobala.jobopen;

import com.example.jobala.resume.ResumeRequest;
import com.example.jobala.skill.Skill;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobopenRepository {
    private final EntityManager em;



    public Jobopen findByJobOpenId(int id) {
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
        String q = """
                insert into jobopen_tb(user_id, edu, career, job_type, salary, hope_job ,comp_location ,content , end_time , jobopen_title, created_at, role) values (?,?,?,?,?,?,?,?,?,?,now(),?)
                """;
        Query query = em.createNativeQuery(q, Jobopen.class);
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

        query.executeUpdate();

        String q2 = """
                select max(id) from jobopen_tb
                """;
        // jobopen id 받기

//        String q3 = """
//                insert into skill_tb(user_id, resume_id, jobopen_id, skills, role) values (?,?,?,?,?)
//                """;
//        Query query2 = em.createNativeQuery(q3, Skill.class);
//        query2.setParameter(1, userId);
//        query2.setParameter(2, reqDTO.getResumeId());
//        query2.setParameter(3, jobopenId);
//        query2.setParameter(4, reqDTO.getSkills());
//        query2.setParameter(5, role);
//        query2.executeUpdate();

    }


    @Transactional
    public void upDate() {
        return;
    }


    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("delete from jobopen_tb where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }


    public Jobopen findByIdWithUser(int id) {
        String a = """
                select * from jobopen_tb where id =?
                """;

        Query query = em.createNativeQuery(a,Jobopen.class);
        query.setParameter(1, id);

        try {
            Jobopen jobopen  = (Jobopen) query.getSingleResult();
            return jobopen;
        } catch (Exception e) {
            return  null;
        }
    }

    public Jobopen findById(Integer id) {
        Query query = em.createNativeQuery("select * from jobopen_tb where id = ?", Jobopen.class);
        query.setParameter(1, id);

        try {
            Jobopen jobopen = (Jobopen) query.getSingleResult();
            return jobopen;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void update(int resumeId, ResumeRequest.UpdateDTO requestDTO) {
        String a = """
                update resume_tb set resume_title=?, hope_job=?, career=?, license=?, content=?, edu=? where id=?
                """;
        Query query = em.createNativeQuery(a);
        query.setParameter(1, requestDTO.getResumeTitle());
        query.setParameter(2, requestDTO.getHopeJob());
        query.setParameter(3, requestDTO.getCareer());
        query.setParameter(4, requestDTO.getLicense());
        query.setParameter(5, requestDTO.getContent());
        query.setParameter(6, requestDTO.getEdu());
        query.setParameter(7, resumeId);
        query.executeUpdate();
    }

}
