package com.example.jobala.apply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplyJPARepository extends JpaRepository<Apply, Integer> {

    @Query("SELECT COUNT(a) FROM Apply a WHERE a.jobopen.id = :jobopenId AND a.role = 0 AND a.state = '검토중'")
    int countJobopenApplyById(@Param("jobopenId") int jobopenId);

//    @Query("SELECT a.id, j.jobopenTitle, r.resumeTitle, r.name, r.edu, j.endTime, a.state, r.id " +
//            "FROM Apply a " +
//            "JOIN FETCH a.jobopen j " +
//            "JOIN FETCH a.resume r " +
//            "WHERE j.user.id = :userId AND a.role = 0 AND a.state = :state")
//    Apply findApplyCompByUserId(@Param("userId") int userId, @Param("state") String state);
//
//
//    @Query("SELECT a.id, j.jobopenTitle, r.resumeTitle, " +
//            "CASE WHEN a.role = 0 THEN r.name ELSE u.compname END, " +
//            "r.edu, j.endTime, a.state, " +
//            "CASE WHEN a.role = 0 THEN j.id ELSE r.id END " +
//            "FROM Apply a " +
//            "JOIN a.jobopen j " +
//            "JOIN a.resume r " +
//            "LEFT JOIN j.user u " +
//            "WHERE (j.user.id = :userId AND a.role = 0 AND a.state = :state) " +
//            "   OR (r.user.id = :userId AND a.role = 1 AND a.state = :state) " +
//            "ORDER BY a.id DESC")
//    List<Apply> findAppliesByUserIdAndState(@Param("userId") int userId, @Param("state") String state);


}
