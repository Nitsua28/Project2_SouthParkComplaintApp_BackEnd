package com.revature.Project2_SouthParkComplaintApp_BackEnd.service;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.Complaint;
import com.revature.Project2_SouthParkComplaintApp_BackEnd.repository.ComplaintRepository;
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
public class ComplaintTest {

    // mock the pet repository bean, pass in the interface/class that we're mocking:
    @MockBean(ComplaintRepository.class)
    private ComplaintRepository complaintRepository;

    // Autowire the PetService, so we have an instance of the petService to test out
    @Autowired ComplaintService complaintService;

    @Test
    public void testInsert() {
        // initialize a pet to insert:
        Complaint complaint = new Complaint("testTitle", "testDescription", "testStatus",1, 9);
        // creating the expected pet object once it is inserted and an id is generated:
        // The value 2 here is completely arbitrary, it's just so we have an actual id that is "generated" from the mocked save method
        Complaint insertedComplaint = new Complaint(1L,"testTitle", "testDescription", "testStatus",1L, 9);

        // mock the save method of the repository, so the repository doesn't actually access the database
        // whenever the .save method is called, it will return the pet object that we created in this test case
        Mockito.when(complaintRepository.save(complaint)).thenReturn(insertedComplaint);

        // ensure that the service returns the updated pet:
        Assertions.assertEquals(insertedComplaint, complaintService.insert(complaint));

    }

    @Test
    public void testGetById() {
        Complaint expectedComplaint = new Complaint("testTitle", "testDescription", "testStatus",1, 9);
        // because findById returns an optional, we have to create a mock optional:
        Optional<Complaint> userOptional = Optional.of(expectedComplaint);
        // So now when we call the findById method it will return an optional of the pet that we created
        Mockito.when(complaintRepository.findById(1L)).thenReturn(userOptional);

        // Finally, call the petService method and assert that the pet we get is the one that we specified using Mocking
        Assertions.assertEquals(expectedComplaint, complaintService.getById(1L));
    }


    @Test
    public void testGetAll() {
        List<Complaint> appUsers = Arrays.asList(
                new Complaint("testTitle", "testDescription", "testStatus",1, 9),
                new Complaint("testTitle2", "testDescription2", "testStatus2",1, 9)
        );

        Mockito.when(complaintRepository.findAll()).thenReturn(appUsers);

        // Finally, call the petService method and assert that the pet we get is the one that we specified using Mocking
        Assertions.assertEquals(appUsers, complaintService.getAll());
    }

    @Test
    public void testUpdate() {

        Complaint complaint = new Complaint("testTitle", "testDescription", "testStatus",1, 9);
        Complaint updatedComplaint = new Complaint("testTitle2", "testDescription2", "testStatus2",1, 9);

        Mockito.when(complaintRepository.save(updatedComplaint)).thenReturn(updatedComplaint);

        // Finally, call the petService method and assert that the pet we get is the one that we specified using Mocking
        Assertions.assertEquals(updatedComplaint, complaintService.update(updatedComplaint));
    }

    @Test
    public void testDelete() {

        Complaint complaint = new Complaint("testTitle", "testDescription", "testStatus",1, 9);

        Mockito.when(complaintRepository.existsById(1L)).thenReturn(true);

        complaintService.delete(1L);
        Mockito.verify(complaintRepository).deleteById(1L);
    }
}
