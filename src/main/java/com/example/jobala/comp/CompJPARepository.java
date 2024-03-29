package com.example.jobala.comp;

import com.example.jobala._user.User;
import com.example.jobala.resume.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompJPARepository extends JpaRepository<User,Integer> {
}
