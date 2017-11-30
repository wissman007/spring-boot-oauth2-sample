package com.gigy.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gigy.model.Person;
import com.gigy.model.User;

import static org.junit.Assert.assertEquals;

import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;
    
    @Autowired
    private UserRepository UserRepository;
    
    @Test
    public void repositorySavesPerson() {
        Person person = new Person();
        person.setName("John");
        person.setAge(25);
        
        Person result = repository.save(person);
        
        assertEquals(result.getName(), "John");
        assertEquals(result.getAge(), 25);
    }
    
    @Test
    public void repositoryGetAllPersons() {
       
        List<Person> results = repository.findAll();
        
        for(Person person: results){
        	System.out.println(person);
        }
        
        List<User> users = UserRepository.findAll();
        
        for (User user: users){
        	System.out.println(user.getUsername() + " " + user.getPassword());
        }
    }

}
