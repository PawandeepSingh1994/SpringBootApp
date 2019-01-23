package com.barclays.resource;

import com.barclays.models.Person;
import com.barclays.services.PersonService;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;



import javax.jms.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class Producer {

    @Autowired
    private JmsTemplate jmstemplate;

    @Autowired
    private Queue queue;


    @Autowired
    private Topic topic;

    @Autowired
    private PersonService personService;


    @PostMapping("/add-person")
    public String publish(@RequestBody Map<String, Object> person) {
        person.put("METHOD", "POST");
       // System.out.println(person.get("occupation"));

        jmstemplate.convertAndSend(topic, person);

        return "Published Successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Integer id){
        Map<String, String> map = new HashMap<>();
        map.put("id", id+"");
        map.put("METHOD", "DELETE");

        jmstemplate.convertAndSend(topic, map);
        return "DELETED SUCCESSFULLY";
    }

    @GetMapping("/get-all")
    public Iterable<Person> getAll() {
        return personService.getAllPersons();
    }

    @GetMapping("get-by-id/{id}")
    public Optional<Person> getById(@PathVariable("id") Integer id) {
        return personService.getPersonById(id);
    }

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

}
