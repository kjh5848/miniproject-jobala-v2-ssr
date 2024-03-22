package com.example.jobala.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SkillJPARepository extends JpaRepository<Skill, Integer> {

    Optional<Skill> findByResumeId(@Param("resumeId") int resumeId);
}
