package com.revature.Project2_SouthParkComplaintApp_BackEnd.service;

import java.util.List;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.AppUser;

public interface AppUserService {
    AppUser insert(AppUser appUser);
    AppUser getById(Long id);
    List<AppUser> getAll();
    AppUser update(AppUser AppUser);
    boolean delete(Long id);
    //List<AppUser> getAll(String flag);
    List<AppUser> findByUsername(String username);
}