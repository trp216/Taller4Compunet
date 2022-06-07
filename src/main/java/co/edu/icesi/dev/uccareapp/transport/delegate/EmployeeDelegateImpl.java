package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;

@Component
public class EmployeeDelegateImpl implements EmployeeDelegate{
    
    public final static String PATH = "http://localhost:8080/api/employee/";

    private RestTemplate rest;

    public EmployeeDelegateImpl(){
        rest = new RestTemplate();
    }

    @Override
    public Employee editEmployee(Employee e){
        rest.put(PATH, e, Employee.class);
        return e;
    }

    @Override
    public Iterable<Employee> findAll(){
        Employee[] e = rest.getForObject(PATH, Employee[].class);
        return Arrays.asList(e);
    }

    @Override
    public void save(Employee e){
        rest.postForEntity(PATH, e, Employee.class);
    }

    @Override
    public Employee findById(Integer id){
        return rest.getForObject(PATH+id, Employee.class);
    }
}
