package co.edu.icesi.dev.uccareapp.transport.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.EmployeeDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.PersonDAO;
import co.edu.icesi.dev.uccareapp.transport.model.person.Person;

@Service
public class PersonServiceImp implements PersonService{
    
    private PersonDAO dao;

    @Autowired
    public PersonServiceImp(PersonDAO pDAO, EmployeeDAO eDao){
        dao = pDAO;
    }

    @Override
    public void save(Person p) {
        dao.save(p);
    }

    @Override
    public Person update(Person p) {
        return dao.update(p);
    }

    @Override
    public void delete(Person p) {
        dao.delete(p);
    }

    @Override
    public Iterable<Person> findAll() {
        return dao.findAll();
    }

    @Override
    public Person findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public Person findByEmployeeId(Integer employeeId) {
        return dao.findByEmployeeId(employeeId);
    }
    
}
