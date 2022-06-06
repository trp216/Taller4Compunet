package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.uccareapp.transport.model.person.Person;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;

@Component
public class PersonDelegateImpl implements PersonDelegate{

    public final static String PATH = "http://localhost:8080/api/person/";

    private RestTemplate rest;

    public PersonDelegateImpl(){
        rest = new RestTemplate();
    }

    @Override
    public Person editPerson(Person person) {
        rest.put(PATH, person, Person.class);
        return null;
    }

    @Override
    public Iterable<Person> findAll() {
        Person[] p = rest.getForObject(PATH, Person[].class);
        return Arrays.asList(p);
    }

    @Override
    public void save(Person person) {
        rest.postForEntity(PATH, person, Salestaxrate.class);
    }

    @Override
    public Person findById(Integer id) {
        return rest.getForObject(PATH+id, Person.class);
    }

 
}
