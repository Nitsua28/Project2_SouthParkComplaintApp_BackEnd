package com.revature.Project2_SouthParkComplaintApp_BackEnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.AppUser;
import com.revature.Project2_SouthParkComplaintApp_BackEnd.service.AppUserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/appuser")

public class AppUserController {
    @Autowired
    AppUserService appUserService;

    @PostMapping()
    public AppUser insert(@RequestBody AppUser appUser) {return appUserService.insert(appUser);};

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<AppUser> getAll(@RequestParam(required = false, value = "flag") String flag) {
//        if(flag == null) return appUserService.getAll();
//            // Otherwise, call the overloaded method:
//        else return appUserService.getAll(flag);
        return appUserService.getAll();
    }

    @GetMapping("/{appUserIdentifier}")
    public AppUser getByIdOrName(@PathVariable("appUserIdentifier") String identifier) {
        try {
            Long id = Long.parseLong(identifier);
            return appUserService.getById(id);
        } catch(Exception e) {
            return appUserService.findByUsername(identifier).get(0);
        }
    }
    @PutMapping()
    public AppUser update(@RequestBody AppUser appUser) {
        return appUserService.update(appUser);
    }

    @DeleteMapping("/{appUserIdentifier}")
    public boolean delete(@PathVariable("appUserIdentifier") String identifier) {
//        return appUserService.delete(id);
        try {
            Long id = Long.parseLong(identifier);
            return appUserService.delete(id);
        } catch(Exception e) {
            return appUserService.delete(appUserService.findByUsername(identifier).get(0).getUser_id());
        }
    }
}