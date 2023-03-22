package com.revature.Project2_SouthParkComplaintApp_BackEnd.controller;

import java.util.List;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ComplaintService complaintService;

    @PostMapping()
    public ResponseEntity insert(@RequestBody Complaint complaint) {
        complaintService.insert(complaint);
        logger.info("Object made: " + complaint.toString());
        return ResponseEntity.status(201).build();
    };

//    @GetMapping()
//    public ResponseEntity<List<Complaint>> getResponse(@RequestParam(required = false) String status) {
//        try {
//            if (status == null) return ResponseEntity.ok(complaintService.getAll());
//            else return ResponseEntity.ok(complaintService.findByStatus(status));
//        }
//        catch (Exception e){
//            return ResponseEntity.status(404).build();
//        }
//
//    }

    @GetMapping()
    public ResponseEntity<List<Complaint>> getResponse(@RequestParam(required = false) String status, @RequestParam(required = false) Long meeting) {
        try {
            if (status == null & meeting == null) {
                logger.info("Success");
                return ResponseEntity.ok(complaintService.getAll());
            }
            else if (status == null) {
                logger.info("Success");
                return ResponseEntity.ok(complaintService.findByMeetingID(meeting));
            }
            else {
                logger.info("Success");
                return ResponseEntity.ok(complaintService.findByStatus(status));
            }
        }
        catch (Exception e){
            logger.error("Not Found");
            return ResponseEntity.status(404).build();
        }

    }
//    @GetMapping("/{complaintIdentifier}")
//    public ResponseEntity<Complaint> getById(@PathVariable("complaintIdentifier") String identifier) {
//        try {
//            Long id = Long.parseLong(identifier);
//            return ResponseEntity.ok(complaintService.getById(id));
//        } catch (Exception e){
//            return ResponseEntity.status(404).build();
//        }
//    }

    @PutMapping()
    public ResponseEntity<Complaint> update(@RequestBody Complaint complaint) {
        try{
            complaintService.getById(complaint.getComplaint_id());
            logger.info("Successfully updated" + complaint.toString());
            return ResponseEntity.ok(complaintService.update(complaint));
        }
        catch(Exception e){
            logger.error("Not Found");
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{appUserIdentifier}")
    public ResponseEntity<Boolean> delete(@PathVariable("appUserIdentifier") String identifier) {

        Long id = Long.parseLong(identifier);
        boolean response = complaintService.delete(id);
        ResponseEntity responseEntity = null;
        if (response){
            logger.info("Success");
            responseEntity = ResponseEntity.ok(true);
        }
        else{
            logger.error("Not Found");
            responseEntity = ResponseEntity.status(404).build();
        }
        return responseEntity;

    }
}