package com.example.jobala.resume;

import com.example.jobala._user.User;
import com.google.gson.Gson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResumeQueryRepository {
    private final EntityManager em;

    public Resume findByUserId(Integer userId) {
        // qlrm -> nativeQuery 수정
        String q = """
                SELECT r FROM Resume r WHERE r.id = (
                    SELECT a.resumeId FROM Apply a WHERE a.userId = :userId
                )
                """;
        return em.createQuery(q, Resume.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

//이미지 받아와서 4
//    public List<Resume> findAllLimt() {
//        String q = """
//                select r.*, p.IMG_FILENAME from resume_tb r join pic_tb p on r.id = p.resume_id order by id desc Limit 4
//                """;
//        Query query = em.createNativeQuery(q, Resume.class);
//        return query.getResultList();
//    }


    public Resume findById(Integer id) {
        Query query = em.createNativeQuery("select * from resume_tb where id = ?", Resume.class);
        query.setParameter(1, id);

        try {
            return (Resume) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Resume findByResumeUserId(User sessionUser) {
        Query query = em.createNativeQuery("select * from resume_tb where id = ?", Resume.class);
        query.setParameter(1, sessionUser.getId());

        Resume resume = (Resume) query.getSingleResult();
        return resume;
    }

//    @Transactional
//    public void save(ResumeRequest.SaveDTO reqDTO, User user) {
//        Query query = em.createNativeQuery("insert into resume_tb(user_id, resume_title, hope_job, career, license, content, edu, name,skills, created_at) values (?,?,?,?,?,?,?,?,?,now())");
//        query.setParameter(1, user.getId());
//        query.setParameter(2, reqDTO.getResumeTitle());
//        query.setParameter(3, reqDTO.getHopeJob());
//        query.setParameter(4, reqDTO.getCareer());
//        query.setParameter(5, reqDTO.getLicense());
//        query.setParameter(6, reqDTO.getContent());
//        query.setParameter(7, reqDTO.getEdu());
//        query.setParameter(8, user.getName());
//        query.setParameter(9, user.getS());
//        query.executeUpdate();
//
//        Query query2 = em.createNativeQuery("select max(id) from resume_tb");
//        Integer resumeId = (Integer) query2.getSingleResult();
//
//    }

    @Transactional
    public void update(int resumeId, ResumeRequest.UpdateDTO reqDTO) {
        Query query = em.createNativeQuery("update resume_tb set resume_title=?, hope_job=?, career=?, license=?, content=?, edu=? where id=?");
        query.setParameter(1, reqDTO.getResumeTitle());
        query.setParameter(2, reqDTO.getHopeJob());
        query.setParameter(3, reqDTO.getCareer());
        query.setParameter(4, reqDTO.getLicense());
        query.setParameter(5, reqDTO.getContent());
        query.setParameter(6, reqDTO.getEdu());
        query.setParameter(7, resumeId);
        query.executeUpdate();

        Query query2 = em.createNativeQuery("select id from skill_tb where resume_id = ?");
        query2.setParameter(1, resumeId);
        Integer skillId = (Integer) query2.getSingleResult();

        Query query3 = em.createNativeQuery("update skill_tb set name= ? where id = ?");
        // List -> JSON
        List<String> skills = reqDTO.getSkills();
        String json = new Gson().toJson(skills);
        System.out.println("제이슨 결과 = " + json);
        query3.setParameter(1, json);
        query3.setParameter(2, skillId);
        query3.executeUpdate();
    }

    public Resume findByResumeId(Integer id) {
        Query query = em.createNativeQuery("select * from resume_tb where id = ?", Resume.class);
        query.setParameter(1, id);

        try {
            return (Resume) query.getSingleResult();
        } catch (Exception e) {
            return null; // 해당 id에 대한 이력서가 없는 경우
        }
    }
}

