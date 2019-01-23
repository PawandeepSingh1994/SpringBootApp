package com.barclays.listener;

import com.barclays.models.Occupation;
import com.barclays.models.Person;
import com.barclays.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class ConsumerThree {

    @Autowired
    PersonService personService;


    @JmsListener(destination = "myTopic.topic")
    public void listener(Map<String, Object> personData) {

        if (personData.get("METHOD").toString().compareTo("POST") == 0) {

            Map<String, Object> map = (Map<String, Object>) personData.get("occupation");
            Occupation occupation = new Occupation(map.get("position").toString(), map.get("location").toString(), Double.parseDouble(map.get("salary").toString()));
            Person personDataToSend = new Person(personData.get("name").toString(), Integer.parseInt(personData.get("age").toString()), occupation);
            System.out.println("Consumed in ConsumerThree: " + personDataToSend);
            personService.addPerson(personDataToSend);

        } else if (personData.get("METHOD").toString().compareTo("DELETE") == 0) {
            Integer id = Integer.parseInt(personData.get("id").toString());
            personService.removePerson(id);
        }
    }

}
