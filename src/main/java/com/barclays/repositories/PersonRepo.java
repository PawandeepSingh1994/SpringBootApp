package com.barclays.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.barclays.models.Person;

@Repository
public interface PersonRepo extends CrudRepository<Person, Integer> {


}
