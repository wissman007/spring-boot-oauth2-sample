package com.gigy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigy.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	List<Person> findAll();
	
	Person findByUsername(String username);

}
