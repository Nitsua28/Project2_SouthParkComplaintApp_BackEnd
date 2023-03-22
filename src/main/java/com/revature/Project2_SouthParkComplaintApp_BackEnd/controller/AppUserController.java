package com.revature.Project2_SouthParkComplaintApp_BackEnd.controller;

import java.util.List;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.LoginBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.AppUser;
import com.revature.Project2_SouthParkComplaintApp_BackEnd.service.AppUserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/appuser")

public class AppUserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    AppUserService appUserService;

    @PostMapping()
    public ResponseEntity insert(@RequestBody AppUser appUser) {
        appUserService.insert(appUser);
        logger.info("Object made: " + appUser.toString());
        return ResponseEntity.status(201).build();
    };

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<AppUser>> getAll() {
//        if(flag == null) return appUserService.getAll();
//            // Otherwise, call the overloaded method:
//        else return appUserService.getAll(flag);
        logger.info("Success");
        return ResponseEntity.ok(appUserService.getAll());
    }

    @GetMapping("/{appUserIdentifier}")
    public ResponseEntity <AppUser> getByIdOrName(@PathVariable("appUserIdentifier") String identifier) {
        try {
            Long id = Long.parseLong(identifier);
            logger.info("Success");
            return ResponseEntity.ok(appUserService.getById(id));
        } catch(Exception e) {
            try{return ResponseEntity.ok(appUserService.findByUsername(identifier).get(0));}
            catch(IndexOutOfBoundsException r){
                logger.error("Not Found");
                return ResponseEntity.status(404).build();
            }

        }

    }
    @PutMapping()
    public ResponseEntity<AppUser> update(@RequestBody AppUser appUser) {
        try{
            appUserService.getById(appUser.getUser_id());
            logger.info("Updated" + appUser.toString());
            return ResponseEntity.ok(appUserService.update(appUser));
        }
        catch(Exception e){
            logger.error("Not Found");
            return ResponseEntity.status(404).build();
        }

    }

    @PatchMapping()
    public ResponseEntity <AppUser> loginWithBody(@RequestBody LoginBody loginBody) {
        AppUser user = null;
        try{user = appUserService.findByUsername(loginBody.getUsername()).get(0);}
        catch(IndexOutOfBoundsException r){
            logger.error("Not Found: 404");
            return ResponseEntity.status(404).build();
        }
        if (!(user.getPassword().equals(loginBody.getPassword()))) {
            logger.error("Password Invalid");
            return ResponseEntity.status(422).build();
        }
        logger.info("Logged in as ", loginBody.getUsername());
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/{appUserIdentifier}")
    public ResponseEntity<Boolean> delete(@PathVariable("appUserIdentifier") String identifier) {
//        return appUserService.delete(id);
        try {
            Long id = Long.parseLong(identifier);
            boolean response = appUserService.delete(id);
            ResponseEntity responseEntity = null;
            if (response){
                logger.info("Success");
                responseEntity = ResponseEntity.ok(true);
            }
            else{
                logger.error("Not Found: 404");
                responseEntity = ResponseEntity.status(404).build();
            }
            return responseEntity;
        } catch(Exception e) {
            try{
                boolean response = appUserService.delete(appUserService.findByUsername(identifier).get(0).getUser_id());
                ResponseEntity responseEntity = null;
                if (response){
                    logger.info("Success");
                    responseEntity = ResponseEntity.ok(true);
                }
                else{
                    logger.error("Not Found: 404");
                    responseEntity = ResponseEntity.status(404).build();
                }
                return responseEntity;
            }
            catch(IndexOutOfBoundsException r) {
                logger.error("Not Found: 404");
                return ResponseEntity.status(404).build();
            }
        }
    }
}