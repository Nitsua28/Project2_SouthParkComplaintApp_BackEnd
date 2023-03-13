package com.revature.Project2_SouthParkComplaintApp_BackEnd.service;

import java.util.List;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.Complaint;

public interface ComplaintService {
    Complaint insert(Complaint complaint);
    Complaint getById(Long id);
    List<Complaint> getAll();
    Complaint update(Complaint complaint);
    boolean delete(Long id);
    List <Complaint> findByStatus(String status);
    List<Complaint> findByMeetingID(Long meeting);
}