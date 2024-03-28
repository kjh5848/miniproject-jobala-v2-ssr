package com.example.jobala.scrap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScrapJPARepository extends JpaRepository<Scrap, Integer> {

    Optional<Scrap> findCompScrapByResumeIdAndUserId(@Param("resumeId") int resumeId, @Param("userId") int userId);
    Optional<Scrap> findGuestScrapByJobopenIdAndUserId(@Param("jobopenId") int jobopenId, @Param("userId") int userId);

}
