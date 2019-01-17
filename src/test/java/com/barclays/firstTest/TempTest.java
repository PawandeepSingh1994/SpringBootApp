package com.barclays.firstTest;

import com.barclays.models.Occupation;
import com.barclays.models.Person;
import com.barclays.services.PersonService;
import com.barclays.stub.PersonServiceStub;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class TempTest {

    @Autowired
    PersonService pStub;

    @Test
    public void getPersonById() {
        Person p = new Person(1, "Michael", 24, new Occupation(1, "Developer", "Barclay's", 500.98));
        assertEquals(pStub.getPersonById(1), p);
    }
}
