package com.revature.Project2_SouthParkComplaintApp_BackEnd.service;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.Complaint;
import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.Meeting;
import com.revature.Project2_SouthParkComplaintApp_BackEnd.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MeetingServiceImpl implements MeetingService{

    @Autowired
    MeetingRepository meetingRepository;


    @Override
    public Meeting insert(Meeting meeting) {return meetingRepository.save(meeting);}

    @Override
    public Meeting getById(Long id) {return meetingRepository.findById(id).get();}

    @Override
    public List<Meeting> getAll() {return meetingRepository.findAll();}

    @Override
    public Meeting update(Meeting meeting){return meetingRepository.save(meeting);}

    public List<Meeting> findByComplaintId(Long complaint){return meetingRepository.findByComplaint(complaint);}
    @Override
    public boolean delete(Long id) {
        boolean found = meetingRepository.existsById(id);
        if(found) meetingRepository.deleteById(id);
        return found;
    }

}