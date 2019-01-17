package com.barclays.services;
import java.util.*;
import com.barclays.models.Person;
import com.barclays.repositories.PersonRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  PersonRepo personRepository;

  @Override
  public Optional<Person> getPersonById(Integer id){

      return personRepository.findById(id);
  }

  @Override
  public Iterable<Person> getAllPersons(){

      return personRepository.findAll();
  }

  @Override
  public void addPerson(Person person){

    personRepository.save(person);

  }

  @Override
  public void removePerson(int id){
    personRepository.deleteById(id);
  }

}
