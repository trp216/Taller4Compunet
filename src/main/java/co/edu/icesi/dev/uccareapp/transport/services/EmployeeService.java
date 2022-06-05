package co.edu.icesi.dev.uccareapp.transport.services;

import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;

public interface EmployeeService {
    public void save(Employee e);
    public Employee edit(Employee e);
    public Iterable<Employee> findAll();
    public Employee findById(Integer id);
    public Employee findByPersonId(Integer personId);
}
