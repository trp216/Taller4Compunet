package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;

public interface IEmployeeDAO {
    void save(Employee entity);
    Employee update(Employee entity);
    void delete(Employee entity);
    Employee findById(Integer id);
    List<Employee> findAll();
}
