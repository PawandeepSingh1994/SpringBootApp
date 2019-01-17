package com.barclays.resource;

import com.barclays.models.Occupation;
import com.barclays.models.Person;
import com.barclays.repositories.PersonRepo;
import com.barclays.services.PersonServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.apache.logging.log4j.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest {

    private static final Logger log = LogManager.getLogger(ServiceTest.class);

    @Mock
    PersonRepo personRepo;

    @InjectMocks
    PersonServiceImpl personServiceImpl;

    private MockMvc mockMvc;

    @InjectMocks
    private Producer producer;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(producer).build();
    }

    @Test
    public void getById() {

        Person personData = new Person(1, "Michael", 24, new Occupation(1, "Developer", "Barclay's", 500.98));

        when(personRepo.findById(1)).thenReturn(java.util.Optional.ofNullable(personData));

        assertEquals(personData, personServiceImpl.getPersonById(1).get());

        log.info("getById() is Hit.");

    }

    @Test
    public void getAll() {

        Person personOne = new Person(1, "Michael", 24, new Occupation(1, "Developer", "Barclay's", 500.98));
        Person personTwo = new Person(2, "Tito", 44, new Occupation(2, "Singer", "Jackoson5", .99));
        Person personThree = new Person(3, "Jackson", 32, new Occupation(3, "Dancer", "SuperBowl", 1000.98));

        ArrayList<Person> list = new ArrayList<>();
        list.add(personOne);
        list.add(personTwo);
        list.add(personThree);
        when(personRepo.findAll()).thenReturn(list);
        assertEquals(list, personServiceImpl.getAllPersons());

        log.info("getAll() is Hit.");

    }

    @Test
    public void hitGetAllAndCheckStatus() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/rest/get-all"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            log.info("hitGetAllAndCheckStatus is Hit.");
        }
        catch (Exception e){
            log.error(e.getMessage() + " Exception Thrown in hitGetAllAndCheckStatus()");
        }
    }

    @Test
    public void hitGetByIdAndCheckStatus() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/rest/get-by-id/1"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string((String) null));
            log.info("hitGetByIdAndCheckStatus() is Hit.");
        }
        catch (Exception e){
            log.error(e.getMessage() + " Exception Thrown in hitGetByIdAndCheckStatus()");
        }
    }

    @Test
    public void hitGetByIdAndCheckIfCalledOnce() {
        Person person = new Person(1, "Michael", 24, new Occupation(1, "Developer", "Barclay's", 500.98));

        when(personRepo.findById(1)).thenReturn(java.util.Optional.ofNullable(person));
        personServiceImpl.getPersonById(1);
        verify(personRepo,times(1)).findById(1);
        verify(personRepo,atMost(1)).findById(1);
        verify(personRepo,atLeast(1)).findById(1);

        log.info("hitGetByIdAndCheckIfCalledOnce() is Hit.");


    }

    @Test
    public void hitAddPersonAndCheckBadRequest() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/rest/add-person"))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
            log.info("hitAddPersonAndCheckBadRequest() is Hit.");

        }
        catch (Exception e){
            log.error(e.getMessage() + " Exception Thrown in hitAddPersonAndCheckBadRequest()");
        }
    }

    @Test
    public void hitDeletePersonAndCheckStatus() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/rest/delete/1"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
            log.info("hitDeletePersonAndCheckStatus() is Hit.");

        }
        catch (Exception e){
            log.error(e.getMessage() + " Exception Thrown in hitDeletePersonAndCheckStatus()");
        }
    }

    @Test
    public void hitAddPersonAndSuccessStatus() {
        try {
            Person person = new Person(1, "Michael", 24, new Occupation(1, "Developer", "Barclay's", 500.98));
            mockMvc.perform(MockMvcRequestBuilders.post("/rest/add-person")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(person)))
                    .andExpect(MockMvcResultMatchers.status().isOk());

            log.info("hitAddPersonAndSuccessStatus() is Hit.");

        }
        catch (Exception e){
            log.error(e.getMessage() + " Exception Thrown in hitAddPersonAndSuccessStatus()");
        }
    }

    public String toJson(Person person){
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(person);
            log.info("toJson() is Hit.");
            return json;
        }
        catch(Exception e){
            log.error(e.getMessage() + " Exception Thrown in toJson(Person person)");
            return null;
        }
    }




}
