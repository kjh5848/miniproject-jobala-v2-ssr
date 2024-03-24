package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala.guest.GuestResponse;
import com.example.jobala.resume.Resume;
import com.google.gson.Gson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobopenQueryRepository {
    private final EntityManager em;

    public List<Resume> findResumeById(User user) {
        Query query = em.createNativeQuery("select * from resume_tb where user_id = ? order by id desc", Resume.class);
        query.setParameter(1, user.getId());

        List<Resume> resumeList2 = query.getResultList();
        return resumeList2;
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


    public List<Jobopen> findAllDesc() {
        String q = """
                select * from jobopen_tb order by id desc;              
                """;
        Query query = em.createNativeQuery(q, Jobopen.class);
        return query.getResultList();
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

    public JobopenResponse.JobopenDetailDTO findByUserAndJobopen(int id) {
        String q = """
            SELECT j.jobopen_title, u.compname, u.img_title, u.img_filename
            FROM user_tb u JOIN jobopen_tb j ON u.id = j.user_id WHERE j.id = ?
            """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);

        JpaResultMapper rm = new JpaResultMapper();
        JobopenResponse.JobopenDetailDTO respDTO = rm.uniqueResult(query, JobopenResponse.JobopenDetailDTO.class);

        // 이미지 파일 제목 경로가 NULL인 경우를 처리하면서 이미지 경로, 제목 디폴트로 변경하고 UUID로 저장되어있으면 분리해서 파일 이름으로 저장
        if (respDTO.getImgFilename() == null) {
            respDTO.setImgFilename("default.png"); // 기본 이미지 경로를 설정
            respDTO.setImgTitle("default.png"); // 기본 이미지 제목을 설정
        } else {
            // 파일 이름과 확장자를 분리
            String[] parts = respDTO.getImgFilename().split("_");
            String imgTitle = parts.length > 1 ? parts[1] : respDTO.getImgFilename(); // 구분자가 없는 경우 기본 파일 이름 사용
            respDTO.setImgTitle(imgTitle);
        }

        return respDTO;
    }

    public List<Jobopen> findJobopenById(User user) {
        Query query = em.createNativeQuery("select * from jobopen_tb where user_id = ? order by id desc", Jobopen.class);
        query.setParameter(1, user.getId());

        List<Jobopen> jobopenList = query.getResultList();
        return jobopenList;
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
