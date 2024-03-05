package com.example.jobala.resume;

import com.example.jobala._user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ResumeRepository {
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


    public void findAll() {
        return;
    }

    public Resume findById(Integer id) {
        Query query = em.createNativeQuery("select * from resume_tb where id = ?", Resume.class);
        query.setParameter(1, id);

        Resume resume = (Resume) query.getSingleResult();
        return resume;
    }

    @Transactional
    public void save(ResumeRequest.SaveDTO resumeSaveDTO, User user) {
        Query query = em.createNativeQuery("insert into resume_tb(user_id, resume_title, hope_job, career, license, content, edu, created_at, name) values (?,?,?,?,?,?,?,now(),?)");
        query.setParameter(1, user.getId());
        query.setParameter(2, resumeSaveDTO.getResumeTitle());
        query.setParameter(3, resumeSaveDTO.getHopeJob());
        query.setParameter(4, resumeSaveDTO.getCareer());
        query.setParameter(5, resumeSaveDTO.getLicense());
        query.setParameter(6, resumeSaveDTO.getContent());
        query.setParameter(7, resumeSaveDTO.getEdu());
        query.setParameter(8, resumeSaveDTO.getEdu());

        Query query2 = em. createNativeQuery("select max(id) from resume_tb");
        Integer resumeId = (Integer) query2.getSingleResult();

        Query query3 = em.createNativeQuery("insert into skill_tb(user_id, role, resume_id, skills) values (?,?,?,?)");
        query3.setParameter(1,user.getId());
        query3.setParameter(2,user.getRole());
        query3.setParameter(3,resumeId);
        query3.setParameter(4,resumeSaveDTO.getSkills());

        query.executeUpdate();
        query3.executeUpdate();
    }

    @Transactional
    public void update(int resumeId, ResumeRequest.UpdateDTO requestDTO) {
        Query query = em.createNativeQuery("update resume_tb set resume_title=?, hope_job=?, career=?, license=?, content=?, edu=? where id=?");
        query.setParameter(1, requestDTO.getResumeTitle());
        query.setParameter(2, requestDTO.getHopeJob());
        query.setParameter(3, requestDTO.getCareer());
        query.setParameter(4, requestDTO.getLicense());
        query.setParameter(5, requestDTO.getContent());
        query.setParameter(6, requestDTO.getEdu());
        query.setParameter(7, resumeId);
        query.executeUpdate();
    }

    @Transactional
    public void delete(int resumeId) {
        Query query = em.createNativeQuery("delete from resume_tb where id = ?");
        query.setParameter(1, resumeId);
        query.executeUpdate();
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

