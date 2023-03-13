package com.revature.Project2_SouthParkComplaintApp_BackEnd.repository;


import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface MeetingRepository extends JpaRepository<Meeting, Long> {


}