package com.revature.Project2_SouthParkComplaintApp_BackEnd.controller;

import java.util.List;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.AppUser;
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
    @Autowired
    MeetingService meetingService;

    @PostMapping()
    public ResponseEntity insert(@RequestBody Meeting meeting) {
        meetingService.insert(meeting);
        return ResponseEntity.status(201).build();
    };

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Meeting>> getAll() {
        return ResponseEntity.ok(meetingService.getAll());
    }

    @GetMapping("/{meetingIdentifier}")
    public ResponseEntity<Meeting> getById(@PathVariable("meetingIdentifier") String identifier) {

        try {
            Long id = Long.parseLong(identifier);
            return ResponseEntity.ok(meetingService.getById(id));
        } catch(Exception e) {
                return ResponseEntity.status(404).build();
        }

    }

    @PutMapping()
    public ResponseEntity<Meeting> update(@RequestBody Meeting meeting) {
        try{
            meetingService.getById(meeting.getMeeting_Id());
            return ResponseEntity.ok(meetingService.update(meeting));
        }
        catch(Exception e){
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{meetingIdentifier}")
    public ResponseEntity<Boolean> delete(@PathVariable("meetingIdentifier") String identifier) {

        Long id = Long.parseLong(identifier);
        boolean response = meetingService.delete(id);
        ResponseEntity responseEntity = null;
        if (response){ responseEntity = ResponseEntity.ok(true);}
        else{ responseEntity = ResponseEntity.status(404).build();}
        return responseEntity;

    }
}