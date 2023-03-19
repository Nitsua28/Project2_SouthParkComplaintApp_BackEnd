package com.revature.Project2_SouthParkComplaintApp_BackEnd.service;


import com.revature.Project2_SouthParkComplaintApp_BackEnd.repository.ComplaintRepository;
import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ComplaintServiceImpl implements ComplaintService{

    @Autowired
    ComplaintRepository complaintRepository;


    @Override
    public Complaint insert(Complaint complaint) {return complaintRepository.save(complaint);}

    @Override
    public Complaint getById(Long id) {return complaintRepository.findById(id).get();}

    @Override
    public List<Complaint> getAll() {return complaintRepository.findAll();}

    @Override
    public Complaint update(Complaint appUser){return complaintRepository.save(appUser);}

    @Override
    public boolean delete(Long id) {
        boolean found = complaintRepository.existsById(id);
        if(found) complaintRepository.deleteById(id);
        return found;
    }

//    @Override
//    public List<AppUser> getAll(String flag) {
//        System.out.println(flag);
//        // this overloaded method takes in a flag and calls the corresponding Repository method:
//        switch(flag) {
//            case "cats":
//                //return employeeRepository.findAll();
//            default:
//                return employeeRepository.findAll();
//        }
//    }
//    public List<Complaint> findByMeetingID(Long meeting){
//        return complaintRepository.findByMeeting(meeting);
//    };
    public List <Complaint> findByStatus(String status) {return complaintRepository.findByStatus(status);}

}