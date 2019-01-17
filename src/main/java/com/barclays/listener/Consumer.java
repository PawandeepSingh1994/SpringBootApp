package com.barclays.listener;

import com.barclays.models.Occupation;
import com.barclays.models.Person;
import com.barclays.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class Consumer {

    @Autowired
    PersonService personService;


    @JmsListener(destination = "inMemory.queue")
    public void listener(Map<String, Object> personData){

        if(personData.get("METHOD").toString().compareTo("POST") == 0) {

            Map<String, Object> map = (Map<String, Object>) personData.get("occupation");
            personService.addPerson(new Person( personData.get("name").toString() , Integer.parseInt(personData.get("age").toString()), new Occupation(map.get("position").toString(), map.get("location").toString(), Double.parseDouble(map.get("salary").toString()))));

        }
        else if(personData.get("METHOD").toString().compareTo("DELETE") == 0) {
            Integer id = Integer.parseInt(personData.get("id").toString());
            personService.removePerson(id);
        }
    }

}