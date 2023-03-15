package com.revature.Project2_SouthParkComplaintApp_BackEnd.controller;

import java.util.List;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity insert(@RequestBody Complaint complaint) {
        complaintService.insert(complaint);
        return ResponseEntity.status(201).build();
    };

    @GetMapping()
    public ResponseEntity<List<Complaint>> getResponse(@RequestParam(required = false) String status, @RequestParam(required = false) Long meeting) {
        try {
            if (status == null & meeting == null) return ResponseEntity.ok(complaintService.getAll());
            else if (status == null) {
                return ResponseEntity.ok(complaintService.findByMeetingID(meeting));
            }
            else return ResponseEntity.ok(complaintService.findByStatus(status));
        }
        catch (Exception e){
            return ResponseEntity.status(404).build();
        }

    }

    @GetMapping("/{complaintIdentifier}")
    public ResponseEntity<Complaint> getById(@PathVariable("complaintIdentifier") String identifier) {
        try {
            Long id = Long.parseLong(identifier);
            return ResponseEntity.ok(complaintService.getById(id));
        } catch (Exception e){
            return ResponseEntity.status(404).build();
        }
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
    public ResponseEntity<Complaint> update(@RequestBody Complaint complaint) {
        try{
            complaintService.getById(complaint.getComplaint_id());
            return ResponseEntity.ok(complaintService.update(complaint));
        }
        catch(Exception e){
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{appUserIdentifier}")
    public ResponseEntity<Boolean> delete(@PathVariable("appUserIdentifier") String identifier) {

        Long id = Long.parseLong(identifier);
        boolean response = complaintService.delete(id);
        ResponseEntity responseEntity = null;
        if (response){ responseEntity = ResponseEntity.ok(true);}
        else{ responseEntity = ResponseEntity.status(404).build();}
        return responseEntity;

    }
}