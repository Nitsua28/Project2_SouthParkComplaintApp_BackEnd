package com.revature.Project2_SouthParkComplaintApp_BackEnd.service;

import java.util.List;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.Complaint;
import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.Meeting;

public interface MeetingService {
    Meeting insert(Meeting meeting);
    Meeting getById(Long id);
    List<Meeting> getAll();
    Meeting update(Meeting meeting);
    boolean delete(Long id);

    List<Meeting> findByComplaintId(Long complaint);

}