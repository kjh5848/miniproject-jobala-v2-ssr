package com.example.jobala.comp;

import com.example.jobala._user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompJPARepository extends JpaRepository<User,Integer> {
}
