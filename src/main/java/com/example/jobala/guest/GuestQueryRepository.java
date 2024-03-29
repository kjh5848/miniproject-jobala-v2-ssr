package com.example.jobala.guest;

import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.Resume;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GuestQueryRepository {
    private final EntityManager em;


    public List<Resume> findResumeById(int userId) {
        Query query = em.createNativeQuery("select * from resume_tb where user_id = ? order by id desc", Resume.class);
        query.setParameter(1, userId);

        List<Resume> resumeList = query.getResultList();
        return resumeList;
    }

    public List<JobopenResponse.ListDTO> findByJoboopenAll() {
        String q = """
                select jb.id, jb.jobopen_title, jb.comp_location, jb.career, jb.edu, ut.img_filename 
                from jobopen_tb jb 
                join user_tb ut on jb.user_id = ut.id
                order by jb.id desc;      
                """;
        Query query = em.createNativeQuery(q);

        JpaResultMapper rm = new JpaResultMapper();
        List<JobopenResponse.ListDTO> jobopenList = rm.list(query, JobopenResponse.ListDTO.class);
        return jobopenList;
    }

    public Page<JobopenResponse.ListDTO> findAll(String skills, GuestResponse.SearchDTO resDTO, Pageable pageable) {
        String skillQuery = """
               SELECT jb.id, jb.jobopen_title, jb.comp_location, jb.career, jb.edu, ut.img_filename 
               FROM jobopen_tb jb 
               INNER JOIN user_tb ut ON jb.user_id = ut.id 
               where (jb.skills Like ? AND jb.skills LIKE ? AND jb.skills LIKE ? AND jb.skills LIKE ? AND jb.skills LIKE ? AND jb.skills LIKE ?) 
               AND (jb.career IN (?, ?)) 
               AND (jb.comp_location IN (?, ?, ?, ?)) 
               AND (jb.edu IN (?, ?)) 
               AND (jb.salary IN (?, ?, ?)) 
               AND (jb.hope_job IN (?, ?)) 
               AND (jb.job_type IN (?, ?, ?)) 
               order by jb.id desc 
               """;
        // skill 파싱
        String[] skill = {"", "", "", "", "", ""};
        String[] skillArr;
        try {
            skillArr = skills.split(",");
            for (int i = 0; i < skillArr.length; i++) {
                skill[i] = skillArr[i];
            }
        } catch (Exception e) {
        }


        // career 파싱
        String[] career = {null , null};
        String[] careerArr;
        try {
            careerArr = resDTO.getCareer().split(",");
            for (int i = 0; i < careerArr.length ; i++) {
                career[i] = careerArr[i];
            }
        } catch (Exception e) {
            career[0] = "신입";
            career[1] = "경력";
        }

        // compLocation 파싱
        String[] compLocation = {null, null, null, null};
        String[] compLocationArr;
        try {
            compLocationArr = resDTO.getCompLocation().split(",");
            for (int i = 0; i < compLocationArr.length ; i++) {
                compLocation[i] = compLocationArr[i];
            }
        } catch (Exception e) {
            compLocation[0] = "서울";
            compLocation[1] = "대전";
            compLocation[2] = "대구";
            compLocation[3] = "부산";
        }

        // edu 파싱
        String[] edu = {null, null};
        String[] eduArr;
        try {
            eduArr = resDTO.getEdu().split(",");
            for (int i = 0; i < eduArr.length ; i++) {
                edu[i] = eduArr[i];
            }
        } catch (Exception e) {
            edu[0] = "고등학교 졸업";
            edu[1] = "대학교 졸업";
        }

        // salary 파싱
        String[] salary = {null, null, null};
        String[] salaryArr;
        try {
            salaryArr = resDTO.getSalary().split(",");
            for (int i = 0; i < salaryArr.length ; i++) {
                salary[i] = salaryArr[i];
            }
        } catch (Exception e) {
            salary[0] = "협의";
            salary[1] = "3000만원 이상";
            salary[2] = "5000만원 이상";
        }

        // hopeJob 파싱
        String[] hopeJob = {null, null};
        String[] hopeJobArr;
        try {
            hopeJobArr = resDTO.getHopeJob().split(",");
            for (int i = 0; i < hopeJobArr.length ; i++) {
                hopeJob[i] = hopeJobArr[i];
            }
        } catch (Exception e) {
            hopeJob[0] = "프론트엔드";
            hopeJob[1] = "백엔드";
        }

        // jobType 파싱
        String[] jobType = {null, null, null};
        String[] jobTypeArr;
        try {
            jobTypeArr = resDTO.getJobType().split(",");
            for (int i = 0; i < jobTypeArr.length ; i++) {
                jobType[i] = jobTypeArr[i];
            }
        } catch (Exception e) {
            jobType[0] = "정규직";
            jobType[1] = "계약직";
            jobType[2] = "인턴";
        }

        Query query = em.createNativeQuery(skillQuery);

        query.setParameter(1, "%"+skill[0]+"%");
        query.setParameter(2, "%"+skill[1]+"%");
        query.setParameter(3, "%"+skill[2]+"%");
        query.setParameter(4, "%"+skill[3]+"%");
        query.setParameter(5, "%"+skill[4]+"%");
        query.setParameter(6, "%"+skill[5]+"%");
        query.setParameter(7, career[0]);
        query.setParameter(8, career[1]);
        query.setParameter(9, compLocation[0]);
        query.setParameter(10, compLocation[1]);
        query.setParameter(11, compLocation[2]);
        query.setParameter(12, compLocation[3]);
        query.setParameter(13, edu[0]);
        query.setParameter(14, edu[1]);
        query.setParameter(15, salary[0]);
        query.setParameter(16, salary[1]);
        query.setParameter(17, salary[2]);
        query.setParameter(18, hopeJob[0]);
        query.setParameter(19, hopeJob[1]);
        query.setParameter(20, jobType[0]);
        query.setParameter(21, jobType[1]);
        query.setParameter(22, jobType[2]);

        JpaResultMapper rm = new JpaResultMapper();
        List<JobopenResponse.ListDTO> jobopenList = rm.list(query, JobopenResponse.ListDTO.class);


        // 결과를 Page 객체에 담아서 반환

        //현재 페이지의 첫번째 검색 결과의 인덱스를 계산한다.
        int start = (int) pageable.getOffset();

        //현재 페이지의 마지막  검색 결과의 인덱스를 구하되, 실제 아이템의 크기를 초과 하지 않도록 한다.
        int end = Math.min((start + pageable.getPageSize()), jobopenList.size());

        // 현재 페이지에 해당하는 검색 결과 들을 sublist로 추출하여 새로운 페이지에 담음
        Page<JobopenResponse.ListDTO> page = new PageImpl<>(jobopenList.subList(start, end), pageable, jobopenList.size());


        return page;
    }
}