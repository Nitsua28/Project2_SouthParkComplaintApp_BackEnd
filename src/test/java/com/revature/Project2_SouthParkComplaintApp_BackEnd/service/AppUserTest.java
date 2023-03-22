package com.revature.Project2_SouthParkComplaintApp_BackEnd.service;

import com.revature.Project2_SouthParkComplaintApp_BackEnd.entity.AppUser;
import com.revature.Project2_SouthParkComplaintApp_BackEnd.repository.AppUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// Make sure we have this @SpringBootTest annotation at the top of our test classes
@SpringBootTest
public class AppUserTest {

    // mock the pet repository bean, pass in the interface/class that we're mocking:
    @MockBean(AppUserRepository.class)
    private AppUserRepository appUserRepository;

    // Autowire the PetService, so we have an instance of the petService to test out
    @Autowired AppUserService appUserService;

    @Test
    public void testInsert() {
        // initialize a pet to insert:
        AppUser user = new AppUser("testUser", "testUser123", "password", "test");
        // creating the expected pet object once it is inserted and an id is generated:
        // The value 2 here is completely arbitrary, it's just so we have an actual id that is "generated" from the mocked save method
        AppUser insertedUser = new AppUser(1L,"testUser", "testUser123", "password", "test");

        // mock the save method of the repository, so the repository doesn't actually access the database
        // whenever the .save method is called, it will return the pet object that we created in this test case
        Mockito.when(appUserRepository.save(user)).thenReturn(insertedUser);

        // ensure that the service returns the updated pet:
        Assertions.assertEquals(insertedUser, appUserService.insert(user));

    }

    @Test
    public void testGetById() {
        AppUser expectedUser = new AppUser(1L,"testUser", "testUser123", "password", "test");
        // because findById returns an optional, we have to create a mock optional:
        Optional<AppUser> userOptional = Optional.of(expectedUser);
        // So now when we call the findById method it will return an optional of the pet that we created
        Mockito.when(appUserRepository.findById(1L)).thenReturn(userOptional);

        // Finally, call the petService method and assert that the pet we get is the one that we specified using Mocking
        Assertions.assertEquals(expectedUser, appUserService.getById(1L));
    }


    @Test
    public void testGetAll() {
        List<AppUser> appUsers = Arrays.asList(
                new AppUser(1L,"testUser", "testUser123", "password", "test"),
                new AppUser(2L,"testUser2", "testUser1234", "password2", "test2")
        );

        Mockito.when(appUserRepository.findAll()).thenReturn(appUsers);

        // Finally, call the petService method and assert that the pet we get is the one that we specified using Mocking
        Assertions.assertEquals(appUsers, appUserService.getAll());
    }

    @Test
    public void testUpdate() {

        AppUser user = new AppUser(1L,"testUser", "testUser123", "password", "test");
        AppUser updatedUser = new AppUser(2L,"testUser2", "testUser1234", "password2", "test2");

        Mockito.when(appUserRepository.save(updatedUser)).thenReturn(updatedUser);

        // Finally, call the petService method and assert that the pet we get is the one that we specified using Mocking
        Assertions.assertEquals(updatedUser, appUserService.update(updatedUser));
    }

    @Test
    public void testDelete() {

        AppUser user = new AppUser(1L,"testUser", "testUser123", "password", "test");


        Mockito.when(appUserRepository.existsById(1L)).thenReturn(true);

        appUserService.delete(1L);
        Mockito.verify(appUserRepository).deleteById(1L);
    }
}
