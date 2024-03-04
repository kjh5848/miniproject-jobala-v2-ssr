package com.example.jobala.resume;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ResumeRepository {
    private final EntityManager em;

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
    public void save(ResumeRequest.SaveDTO resumeSaveDTO, int userId) {
        Query query = em.createNativeQuery("insert into resume_tb(user_id, resume_title, hope_job, career, license, content, edu, created_at) values (?,?,?,?,?,?,?,now())");
        System.out.println(resumeSaveDTO);
        query.setParameter(1, userId);
        query.setParameter(2, resumeSaveDTO.getResumeTitle());
        query.setParameter(3, resumeSaveDTO.getHopeJob());
        query.setParameter(4, resumeSaveDTO.getCareer());
        query.setParameter(5, resumeSaveDTO.getLicense());
        query.setParameter(6, resumeSaveDTO.getContent());
        query.setParameter(7, resumeSaveDTO.getEdu());

        query.executeUpdate();
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

