package com.example.jobala.guest;

import com.example.jobala.resume.Resume;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GuestRepository {
    private final EntityManager entityManager;

    public List<Resume> findResumeAll() {
        Query query = entityManager.createNativeQuery("select * from resume_tb order by id desc", Resume.class);

        List<Resume> resumeList = query.getResultList();
        return resumeList;
    }

    public void findAll() {
        return;
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
