package co.edu.icesi.dev.uccareapp.transport.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
