package com.example.jobala.guest;

import com.example.jobala._user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestJPARepository extends JpaRepository<User,Integer> {

}
