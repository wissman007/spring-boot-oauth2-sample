package com.gigy.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import com.gigy.model.Person;
import com.gigy.repository.PersonRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonController.class, secure = false)
public class PersonControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PersonRepository personRepo;
	
	private Person person;
	private Optional<Person> optionalPerson;
	
	@Before
	public void prepare() {
		person = new Person();
		person.setId(1l);
		person.setName("John");
		person.setAge(25);
		optionalPerson.orElse(person);
	}

	@Test
	public void getPersonTest() throws Exception {
		given(personRepo.findById(1l)).willReturn(optionalPerson);
		mvc.perform(get("/people/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("John")))
				.andExpect(jsonPath("$.age", is(25)));
	}
	
	@Test
	public void personNotFoundTest() throws Exception {
		mvc.perform(get("/people/2").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());
	}
	
	@Test
	public void getPeopleTest() throws Exception {
		List<Person> people = new ArrayList<Person>();
		people.add(person);
		
		given(personRepo.findAll()).willReturn(people);
		mvc.perform(get("/people").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("John")))
				.andExpect(jsonPath("$[0].age", is(25)));
	}

}
