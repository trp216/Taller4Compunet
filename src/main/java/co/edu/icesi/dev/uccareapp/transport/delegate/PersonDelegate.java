package co.edu.icesi.dev.uccareapp.transport.delegate;

import co.edu.icesi.dev.uccareapp.transport.model.person.Person;

public interface PersonDelegate {
    public Person editPerson(Person person);
    public Iterable<Person> findAll();
    public void save(Person person);
    public Person findById(Integer id);
    public Person findByEmployeeId(Integer id);
}
