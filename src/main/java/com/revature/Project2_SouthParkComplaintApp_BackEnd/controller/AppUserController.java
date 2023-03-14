package com.revature.Project2_SouthParkComplaintApp_BackEnd.controller;

import java.util.List;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.LoginBody;
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
    @Autowired
    AppUserService appUserService;

    @PostMapping()
    public ResponseEntity insert(@RequestBody AppUser appUser) {
        appUserService.insert(appUser);
        return ResponseEntity.status(201).build();
    };

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<AppUser>> getAll() {
//        if(flag == null) return appUserService.getAll();
//            // Otherwise, call the overloaded method:
//        else return appUserService.getAll(flag);
        return ResponseEntity.ok(appUserService.getAll());
    }

    @GetMapping("/{appUserIdentifier}")
    public ResponseEntity <AppUser> getByIdOrName(@PathVariable("appUserIdentifier") String identifier) {
        try {
            Long id = Long.parseLong(identifier);
            return ResponseEntity.ok(appUserService.getById(id));
        } catch(Exception e) {
            try{return ResponseEntity.ok(appUserService.findByUsername(identifier).get(0));}
            catch(IndexOutOfBoundsException r){
                return ResponseEntity.status(404).build();
            }

        }

    }
    @PutMapping()
    public ResponseEntity<AppUser> update(@RequestBody AppUser appUser) {
        try{return ResponseEntity.ok(appUserService.update(appUser));}
        catch(Exception e){
            return ResponseEntity.status(404).build();
        }

    }

    @PatchMapping()
    public ResponseEntity <AppUser> loginWithBody(@RequestBody LoginBody loginBody) {
        AppUser user = null;
        try{user = appUserService.findByUsername(loginBody.getUsername()).get(0);}
        catch(IndexOutOfBoundsException r){
            return ResponseEntity.status(404).build();
        }
        if (!(user.getPassword().equals(loginBody.getPassword()))) return ResponseEntity.status(422).build();
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/{appUserIdentifier}")
    public ResponseEntity<Boolean> delete(@PathVariable("appUserIdentifier") String identifier) {
//        return appUserService.delete(id);
        try {
            Long id = Long.parseLong(identifier);
            boolean response = appUserService.delete(id);
            ResponseEntity responseEntity = null;
            if (response){ responseEntity = ResponseEntity.ok(true);}
            else{ responseEntity = ResponseEntity.status(404).build();}
            return responseEntity;
        } catch(Exception e) {
            try{
                boolean response = appUserService.delete(appUserService.findByUsername(identifier).get(0).getUser_id());
                ResponseEntity responseEntity = null;
                if (response){ responseEntity = ResponseEntity.ok(true);}
                else{ responseEntity = ResponseEntity.status(404).build();}
                return responseEntity;
            }
            catch(IndexOutOfBoundsException r) {
                return ResponseEntity.status(404).build();
            }
        }
    }
}