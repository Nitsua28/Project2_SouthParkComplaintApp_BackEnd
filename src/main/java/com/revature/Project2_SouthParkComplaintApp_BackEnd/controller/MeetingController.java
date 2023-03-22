package com.revature.Project2_SouthParkComplaintApp_BackEnd.controller;

import java.util.List;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.AppUser;
import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.Complaint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.Meeting;
import com.revature.Project2_SouthParkComplaintApp_BackEnd.service.MeetingService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/meeting")

public class MeetingController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MeetingService meetingService;

    @PostMapping()
    public ResponseEntity insert(@RequestBody Meeting meeting) {
        meetingService.insert(meeting);
        logger.info("Object made: " + meeting.toString());
        return ResponseEntity.status(201).build();
    };

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Meeting>> getAll() {
        logger.info("Success");
        return ResponseEntity.ok(meetingService.getAll());
    }

    @GetMapping("/{meetingIdentifier}")
    public ResponseEntity<Meeting> getById(@PathVariable("meetingIdentifier") String identifier) {

        try {
            Long id = Long.parseLong(identifier);
            logger.info("Success");
            return ResponseEntity.ok(meetingService.getById(id));
        } catch(Exception e) {
            logger.error("Not Found");
            return ResponseEntity.status(404).build();
        }

    }
//    @GetMapping()
//    public ResponseEntity<List<Meeting>> getResponse(@RequestParam(required = false) Long complaintId) {
//        try {
//            if (complaintId != null) return ResponseEntity.ok(meetingService.findByComplaintId(complaintId));
//            else return ResponseEntity.ok(meetingService.getAll());
//        }
//        catch (Exception e){
//            return ResponseEntity.status(404).build();
//        }
//
//    }
    @PutMapping()
    public ResponseEntity<Meeting> update(@RequestBody Meeting meeting) {
        try{
            meetingService.getById(meeting.getMeeting_Id());
            logger.info("Updated" + meeting.toString());
            return ResponseEntity.ok(meetingService.update(meeting));
        }
        catch(Exception e){
            logger.error("Not Found");
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{meetingIdentifier}")
    public ResponseEntity<Boolean> delete(@PathVariable("meetingIdentifier") String identifier) {

        Long id = Long.parseLong(identifier);
        boolean response = meetingService.delete(id);
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