package com.barclays.services;

import com.barclays.models.Person;
import java.util.Optional;

public interface PersonService {

  Optional<Person> getPersonById(Integer id);
  Iterable<Person> getAllPersons();
  void addPerson(Person person);
  void removePerson(int id);

}
