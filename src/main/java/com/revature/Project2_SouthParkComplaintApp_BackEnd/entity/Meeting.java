package com.revature.Project2_SouthParkComplaintApp_BackEnd.entity;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
//    @ElementCollection
//    private List<String> attendees = new ArrayList<>();
//    @ElementCollection
//    private List<String> speakers = new ArrayList<>();;
    public Meeting(String address, Integer time, String summary) {
        this.address = address;
        this.time = time;
        this.summary = summary;

    }
}