package co.edu.icesi.dev.uccareapp.transport.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.EmployeeDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.PersonDAO;
import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;
import co.edu.icesi.dev.uccareapp.transport.model.person.Person;

@Service
public class PersonServiceImp implements PersonService{
    
    private PersonDAO dao;
    private EmployeeDAO eDao;

    @Autowired
    public PersonServiceImp(PersonDAO pDAO, EmployeeDAO eDao){
        dao = pDAO;
        this.eDao = eDao;
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
    public Person findByEmployeeId(Integer id) {
        Employee found = eDao.findById(id);
        Person ret = null;
        if(found != null){
            //TODO Add Person.findByEmployeeId in DAO
            //ret = dao.find
        }
        return ret;
    }
    
}
