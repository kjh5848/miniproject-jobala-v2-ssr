package com.example.jobala.jobopen;

import com.example.jobala.resume.ResumeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobopenJPARepository extends JpaRepository<Jobopen, Integer> {

    //공고 찾아오기
    Optional<Jobopen> findJobopenById(@Param("id")int id);

    @Query("SELECT j FROM Jobopen j JOIN fetch j.scraps s WHERE s.user.id = :userId")
    List<JobopenResponse.ScrapDTO> findByUserIdJoinScrap(@Param("userId") int userId);

    // 사용자 ID를 이용하여 채용공고 정보와 함께 사용자 정보를 가져오는 쿼리
    @Query("SELECT j FROM Jobopen j JOIN FETCH j.user WHERE j.id = :jobopenId")
    Optional<Jobopen> findByJobopenIdWithUser(@Param("jobopenId") int jobopenId);


}