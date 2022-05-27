package co.edu.icesi.dev.uccareapp.transport.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
