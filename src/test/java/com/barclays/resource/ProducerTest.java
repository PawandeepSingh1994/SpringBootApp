package com.barclays.resource;

import com.barclays.models.Occupation;
import com.barclays.models.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProducerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private Producer producer;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(producer).build();
    }

    @Test
    public void publish() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void getById() throws Exception{
        Person p = new Person(1, "Michael", 24, new Occupation(1, "Developer", "Barclay's", 500.98));
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("HelloWorld"));
    }
}