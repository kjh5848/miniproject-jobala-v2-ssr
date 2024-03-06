package com.example.jobala.comp;

import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CompRepository {
    private final EntityManager em;

    public List<Resume> findResumeById(int userId) {
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


        public List<ResumeResponse.DetailDTO> findResumeAll2() {
            String q = "SELECT r FROM Resume r ORDER BY r.id DESC";
            List<Resume> resumes = em.createQuery(q, Resume.class).getResultList();

            return resumes.stream().map(this::toDTO).collect(Collectors.toList());
        }

        private ResumeResponse.DetailDTO toDTO(Resume resume) {
            ResumeResponse.DetailDTO dto = new ResumeResponse.DetailDTO();
            dto.setUserId(resume.getId());
            dto.setResumeTitle(resume.getResumeTitle());
            dto.setCareer(resume.getCareer());
            dto.setEdu(resume.getEdu());
            return dto;
        }
    }

