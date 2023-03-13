package com.revature.Project2_SouthParkComplaintApp_BackEnd.entity;

import lombok.*;
import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name= "meeting")
public class Meeting {
    @Id
    private Long meeting_Id;
    private String address;
    private Integer time;
    private String summary;

}