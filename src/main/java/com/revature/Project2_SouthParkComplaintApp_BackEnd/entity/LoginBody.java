package com.revature.Project2_SouthParkComplaintApp_BackEnd.entity;

import lombok.*;
import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity

public class LoginBody {
    @Id
    private String username;
    private String password;

}