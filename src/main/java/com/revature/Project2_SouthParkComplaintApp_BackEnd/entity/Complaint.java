package com.revature.Project2_SouthParkComplaintApp_BackEnd.entity;

import lombok.*;
import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name= "complaint")
public class Complaint {
    @Id
    private Long complaint_id;
    private String title;
    private String description;
    private String status;
    private Long meeting;
    private Integer priority;

}