package com.revature.Project2_SouthParkComplaintApp_BackEnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Meeting insert(@RequestBody Meeting meeting) {return meetingService.insert(meeting);};

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Meeting> getAll(@RequestParam(required = false, value = "flag") String flag) {
//        if(flag == null) return appUserService.getAll();
//            // Otherwise, call the overloaded method:
//        else return appUserService.getAll(flag);
        return meetingService.getAll();
    }

    @GetMapping("/{meetingIdentifier}")
    public Meeting getById(@PathVariable("meetingIdentifier") String identifier) {

        Long id = Long.parseLong(identifier);
        return meetingService.getById(id);

    }

    @PutMapping()
    public Meeting update(@RequestBody Meeting meeting) {return meetingService.update(meeting);}

    @DeleteMapping("/{meetingIdentifier}")
    public boolean delete(@PathVariable("meetingIdentifier") String identifier) {

        Long id = Long.parseLong(identifier);
        return meetingService.delete(id);

    }
}