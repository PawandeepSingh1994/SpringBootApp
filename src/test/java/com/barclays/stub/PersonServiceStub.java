package com.barclays.stub;

import com.barclays.models.Occupation;
import com.barclays.models.Person;
import com.barclays.repositories.PersonRepo;
import com.barclays.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

public class PersonServiceStub implements PersonService {

    @Autowired
    PersonRepo personRepository;

    private ArrayList<Person> list = new ArrayList<>();

    public PersonServiceStub() {
        this.list.add(new Person(1, "Michael", 24, new Occupation(1, "Developer", "Barclay's", 500.98)));
        this.list.add(new Person(2, "Shaun", 45, new Occupation(2, "Developer", "Barclay's", 87000.98)));
        this.list.add(new Person(3, "Tony", 34, new Occupation(3, "Developer", "Barclay's", 187000.98)));

    }

    public Optional<Person> getPersonById(Integer id){
       return personRepository.findById(id);
    }

    public Iterable<Person> getAllPersons(){
        return this.list;
    }

    public void addPerson(Person person) {

    }

    public void removePerson(int id){

    }

}
