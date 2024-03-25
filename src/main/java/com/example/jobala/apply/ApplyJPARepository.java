package com.example.jobala.apply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplyJPARepository extends JpaRepository<Apply, Integer> {


    @Query("SELECT COUNT(a) FROM Apply a WHERE a.jobopen.id = :jobopenId AND a.role = 0 AND a.state = '검토중'")
    int countJobopenApplyById(@Param("jobopenId") int jobopenId);

    @Query("SELECT j.jobopenTitle, r.resumeTitle, r.id AS resumeId, a.state, r.name, r.edu, j.endTime\n" +
            "FROM Apply a\n" +
            "JOIN a.user u\n" +
            "JOIN a.resume r\n" +
            "JOIN a.jobopen j\n" +
            "WHERE u.id = :userId")
    List<Apply> findApplyCompByUserId(@Param("userId") int userId);
}
