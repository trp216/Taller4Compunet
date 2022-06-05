package co.edu.icesi.dev.uccareapp.transport.services;

import co.edu.icesi.dev.uccareapp.transport.model.person.Person;

public interface PersonService {
    public void save(Person p);
    public Person update(Person p);
    public void delete(Person p);
    public Iterable<Person> findAll();
    public Person findById(Integer id);
    public Person findByEmployeeId(Integer id);
}
