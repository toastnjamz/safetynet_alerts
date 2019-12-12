package com.safetynet.alerts.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.domain.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

}
