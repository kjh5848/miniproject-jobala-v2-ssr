package com.example.jobala.apply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplyJPARepository extends JpaRepository<Apply, Integer> {

    // 로그인한 기업 ID로 지원서 조회
//    @Query("SELECT new com.example.jobala.apply.ApplyResponse.ApplyDTO(" +
//            "a.id, j.title, r.title, u.name, r.education, j.endTime, a.state, r.id) " +
//            "FROM Apply a JOIN a.jobopen j JOIN a.resume r JOIN a.user u " +
//            "WHERE j.user.id = :compId AND a.role = 0 AND a.state = :state")
//    List<ApplyResponse.ApplyDTO> findApplyCompByUserId(@Param("compId") int compId, @Param("state") String state);
//
//    // 로그인한 User ID로 지원서 조회
//    @Query("SELECT new com.example.jobala.apply.ApplyResponse.ApplyDTO(" +
//            "a.id, j.title, r.title, u.name, r.education, j.endTime, a.state, r.id) " +
//            "FROM Apply a JOIN a.jobopen j JOIN a.resume r JOIN a.user u " +
//            "WHERE a.user.id = :userId ORDER BY a.id DESC")
//    List<ApplyResponse.ApplyDTO> findByUserId(@Param("userId") int userId);
//
//    // 제안 받은 공고 확인 (로그인한 User ID와 상태로 조회)
//    @Query("SELECT new com.example.jobala.apply.ApplyResponse.ApplyDTO2(" +
//            "a.id, j.title, r.title, j.companyName, r.education, j.endTime, a.state, j.id) " +
//            "FROM Apply a JOIN a.jobopen j JOIN a.resume r " +
//            "WHERE r.user.id = :userId AND a.role = 1 AND a.state = :state")
//    List<ApplyResponse.ApplyDTO2> findJobOpenByUserId(@Param("userId") int userId, @Param("state") String state);
}