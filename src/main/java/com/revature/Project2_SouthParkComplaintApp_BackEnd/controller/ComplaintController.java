package com.revature.Project2_SouthParkComplaintApp_BackEnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.Complaint;
import com.revature.Project2_SouthParkComplaintApp_BackEnd.service.ComplaintService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/complaint")

public class ComplaintController {
    @Autowired
    ComplaintService complaintService;

    @PostMapping()
    public Complaint insert(@RequestBody Complaint complaint) {return complaintService.insert(complaint);};

    @GetMapping()
    public List<Complaint> getResponse(@RequestParam(required = false) String status,@RequestParam(required = false) Long meeting) {
        if(status == null & meeting == null) return complaintService.getAll();
        else if (status == null) {
            return complaintService.findByMeetingID(meeting);

        } else return complaintService.findByStatus(status);

    }

    @GetMapping("{complaintIdentifier}")
    public Complaint getById(@PathVariable("complaintIdentifier") String identifier) {
//        try {
            Long id = Long.parseLong(identifier);
            return complaintService.getById(id);
//    } catch(Exception e) {
//        return complaintService.findByStatus(identifier);
//        }
    }
//    @RequestMapping(value = "", method = RequestMethod.GET)
////    @ResponseBody
//    public Complaint getByStatus(@RequestParam String status) {
//        return complaintService.findByStatus(status);
//    }

//    @GetMapping("/meetingId={complaintIdentifier}")
//    public Complaint getByMeetingId(@PathVariable("complaintIdentifier") String identifier) {
//
//        Long id = Long.parseLong(identifier);
//        return (Complaint) complaintService.findByMeetingID(id);
//
//    }
    @PutMapping()
    public Complaint update(@RequestBody Complaint complaint) {return complaintService.update(complaint);}

    @DeleteMapping("/{appUserIdentifier}")
    public boolean delete(@PathVariable("appUserIdentifier") String identifier) {

            Long id = Long.parseLong(identifier);
            return complaintService.delete(id);

    }
}