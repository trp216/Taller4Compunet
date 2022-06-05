package co.edu.icesi.dev.uccareapp.transport.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.EmployeeDAO;
import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;

@Service
public class EmployeeServiceImp implements EmployeeService{

    @Autowired
    private EmployeeDAO dao;

    @Autowired
    public EmployeeServiceImp(EmployeeDAO dao){
        super();
        this.dao = dao;
    }

    @Override
    public void save(Employee e) {
       dao.save(e);
    }

    @Override
    public Employee edit(Employee e) {
        return dao.update(e);
    }

    @Override
    public Iterable<Employee> findAll() {
        return dao.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public Employee findByPersonId(Integer personId){
        return dao.findByPersonId(personId);
    }
}
