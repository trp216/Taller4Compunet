package co.edu.icesi.dev.uccareapp.transport.delegate;

import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;

public interface EmployeeDelegate {
    public Employee editEmployee(Employee e);
    public Iterable<Employee> findAll();
    public void save(Employee e);
    public Employee findById(Integer id);
}
