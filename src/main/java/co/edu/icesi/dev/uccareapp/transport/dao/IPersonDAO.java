package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import co.edu.icesi.dev.uccareapp.transport.model.person.Person;

public interface IPersonDAO {
    void save(Person entity);
    Person update(Person entity);
    void delete(Person entity);
    Person findById(Integer id);
    List<Person> findAll();    
}
