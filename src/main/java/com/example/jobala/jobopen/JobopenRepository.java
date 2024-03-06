package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala.resume.Resume;
import com.google.gson.Gson;
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
        String q = """
                select * from jobopen_tb where id =?
                """;

        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);
        Jobopen jobopen = (Jobopen) query.getSingleResult();
        return jobopen;
    }

    @Transactional
    public void save(JobopenRequest.SaveDTO reqDTO, User sessionUser) {
        // jobopen인설트
        String q = """
                insert into jobopen_tb(user_id, edu, career, job_type, salary, hope_job ,comp_location ,content , end_time , jobopen_title, created_at, role) values (?,?,?,?,?,?,?,?,?,?,now(),?)
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, sessionUser.getId());
        query.setParameter(2, reqDTO.getEdu());
        query.setParameter(3, reqDTO.getCareer());
        query.setParameter(4, reqDTO.getJobType());
        query.setParameter(5, reqDTO.getSalary());
        query.setParameter(6, reqDTO.getHopeJob());
        query.setParameter(7, reqDTO.getCompLocation());
        query.setParameter(8, reqDTO.getContent());
        query.setParameter(9, reqDTO.getEndTime());
        query.setParameter(10, reqDTO.getJobopenTitle());
        query.setParameter(11, sessionUser.getRole());
        query.executeUpdate();

        // jobopen id 받기
        String q2 = """
                select max(id) from jobopen_tb
                """;
        Query query2 = em.createNativeQuery(q2);
        Integer jobopenId = (Integer) query2.getSingleResult();

        //스킬 insert
        String q3 = """
                insert into skill_tb(user_id, role, jobopen_id, name) values (?,?,?,?)
                """;
        Query query3 = em.createNativeQuery(q3);

        // List -> JSON
        List<String> skills = reqDTO.getSkills();
        String json = new Gson().toJson(skills);
        System.out.println("제이슨 결과 = " + json);


        query3.setParameter(1, sessionUser.getId());
        query3.setParameter(2, sessionUser.getRole());
        query3.setParameter(3, jobopenId);
        query3.setParameter(4, json);
        query3.executeUpdate();

    }

    @Transactional
    public void update() {
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
        Query query = em.createNativeQuery(a, Jobopen.class);
        query.setParameter(1, id);
        try {
            Jobopen jobopen = (Jobopen) query.getSingleResult();
            return jobopen;
        } catch (Exception e) {
            return null;
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
    public void update(Jobopen jobopenId, JobopenRequest.UpdateDTO reqDTO) {
        String a = """
                update jobopen_tb set compname = ? ,jobopenTitle=? , career=?, edu=?, jobType=?,salary=?,compLocation=?,content=? ,skills =? where id=?
                """;
        Query query = em.createNativeQuery(a);
        query.setParameter(1, reqDTO.getCompname());
        query.setParameter(2, reqDTO.getJobopenTitle());
        query.setParameter(3, reqDTO.getCareer());
        query.setParameter(4, reqDTO.getEdu());
        query.setParameter(5, reqDTO.getJobType());
        query.setParameter(6, reqDTO.getSalary());
        query.setParameter(7, reqDTO.getCompLocation());
        query.setParameter(8, reqDTO.getContent());
        query.setParameter(9, reqDTO.getSkills());
        query.setParameter(10, jobopenId);
        query.executeUpdate();
    }

    public List<Jobopen> findAll() {
        String q = """
                select * from jobopen_tb order by id desc;              
                """;
        Query query = em.createNativeQuery(q, Jobopen.class);
        return query.getResultList();
    }

    public List<Resume> findByResumeAll() {
        String q = """
                select * from resume_tb order by id desc;              
                """;
        Query query = em.createNativeQuery(q, Resume.class);
        return query.getResultList();
    }

//    public JobopenResponse.DetailDTO findByWithJobopen(int idx) {
//        String q = """
//                select
//                j.id,
//                j.compname,
//                j.jobopen_title,
//                j.career,
//                j.edu,
//                j.job_type,
//                j.salary,
//                j.comp_location,
//                j.content,
//                j.hope_job,
//                s.name
//                from jobopen_tb j
//                inner join skill_tb s on j.id= s.jobopen_id
//                where j.id= ?
//                """;
//        Query query = em.createNativeQuery(q);
//        query.setParameter(1, idx);
////
////        Object[] row = (Object[]) query.getSingleResult();
////
////        Integer id = (Integer) row[0];
////        String  compname = (String) row[1];
////        String jobopenTitle = (String) row[2];
////        String career = (String) row[3];
////        String edu = (String) row[4];
////        String jobType = (String) row[5];
////        String salary = (String) row[6];
////        String compLocation = (String) row[7];
////        String content = (String) row[8];
////        String hopeJob = (String) row[9];
////        String name = (String) row[10];
////
////        JobopenResponse.DetailDTO respDTO = new JobopenResponse.DetailDTO();
////        respDTO.setId(id);
////        respDTO.setCompname(compname);
////        respDTO.setJobopenTitle(jobopenTitle);
////        respDTO.setCareer(career);
////        respDTO.setEdu(edu);
////        respDTO.setJobType(jobType);
////        respDTO.setSalary(salary);
////        respDTO.setCompLocation(compLocation);
////        respDTO.setContent(content);
////        respDTO.setHopeJob(hopeJob);
////        respDTO.setName(name);
////
//
//
//        return respDTO;
//    }
}
