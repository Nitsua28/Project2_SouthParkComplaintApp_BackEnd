package com.revature.Project2_SouthParkComplaintApp_BackEnd.service;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.Meeting;
import com.revature.Project2_SouthParkComplaintApp_BackEnd.repository.MeetingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// Make sure we have this @SpringBootTest annotation at the top of our test classes
@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class MeetingTest {

    // mock the pet repository bean, pass in the interface/class that we're mocking:
    @MockBean(MeetingRepository.class)
    private MeetingRepository meetingRepository;

    // Autowire the PetService, so we have an instance of the petService to test out
    @Autowired MeetingService meetingService;

    @Test
    public void testInsert() {
        // initialize a pet to insert:
        Meeting meeting = new Meeting("testAddress", 2, "testSummary");
        // creating the expected pet object once it is inserted and an id is generated:
        // The value 2 here is completely arbitrary, it's just so we have an actual id that is "generated" from the mocked save method
        Meeting insertedMeeting = new Meeting(1L,"testAddress", 2, "testSummary");

        // mock the save method of the repository, so the repository doesn't actually access the database
        // whenever the .save method is called, it will return the pet object that we created in this test case
        Mockito.when(meetingRepository.save(meeting)).thenReturn(insertedMeeting);

        // ensure that the service returns the updated pet:
        Assertions.assertEquals(insertedMeeting, meetingService.insert(meeting));

    }

    @Test
    public void testGetById() {
        Meeting expectedMeeting = new Meeting("testAddress", 2, "testSummary");
        // because findById returns an optional, we have to create a mock optional:
        Optional<Meeting> userOptional = Optional.of(expectedMeeting);
        // So now when we call the findById method it will return an optional of the pet that we created
        Mockito.when(meetingRepository.findById(1L)).thenReturn(userOptional);

        // Finally, call the petService method and assert that the pet we get is the one that we specified using Mocking
        Assertions.assertEquals(expectedMeeting, meetingService.getById(1L));
    }


    @Test
    public void testGetAll() {
        List<Meeting> appUsers = Arrays.asList(
                new Meeting("testAddress", 2, "testSummary"),
                new Meeting("testAddress2", 4, "testSummary2")
        );

        Mockito.when(meetingRepository.findAll()).thenReturn(appUsers);

        // Finally, call the petService method and assert that the pet we get is the one that we specified using Mocking
        Assertions.assertEquals(appUsers, meetingService.getAll());
    }

    @Test
    public void testUpdate() {

        Meeting meeting = new Meeting("testAddress", 2, "testSummary");
        Meeting updatedMeeting = new Meeting("testAddress2", 4, "testSummary2");

        Mockito.when(meetingRepository.save(updatedMeeting)).thenReturn(updatedMeeting);

        // Finally, call the petService method and assert that the pet we get is the one that we specified using Mocking
        Assertions.assertEquals(updatedMeeting, meetingService.update(updatedMeeting));
    }

    @Test
    public void testDelete() {

        Meeting meeting = new Meeting("testAddress", 2, "testSummary");

        Mockito.when(meetingRepository.existsById(1L)).thenReturn(true);

        meetingService.delete(1L);
        Mockito.verify(meetingRepository).deleteById(1L);
    }
}
