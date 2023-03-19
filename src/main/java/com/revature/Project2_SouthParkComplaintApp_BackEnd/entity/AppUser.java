package com.revature.Project2_SouthParkComplaintApp_BackEnd.entity;

import lombok.*;
import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name= "app_user")
public class AppUser {
    @Id
    private Long user_id;
    private String name;
    private String username;
    private String password;
    private String role;

}