package com.revature.Project2_SouthParkComplaintApp_BackEnd.repository;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.AppUser;
import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByStatus(String status);
    //List<Complaint> findByMeeting(Long meeting);

}