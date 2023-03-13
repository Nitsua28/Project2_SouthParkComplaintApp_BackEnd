package com.revature.Project2_SouthParkComplaintApp_BackEnd.repository;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findByUsername(String username);


}