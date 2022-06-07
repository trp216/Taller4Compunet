package co.edu.icesi.dev.uccareapp.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;
import co.edu.icesi.dev.uccareapp.transport.services.EmployeeServiceImp;

@RestController 
public class EmployeeRestController {
    
    @Autowired
    private EmployeeServiceImp eService;

    @RequestMapping(value="/api/employee/", method=RequestMethod.GET)
    public Iterable<Employee> findAll(){
        return eService.findAll();
    }

    @RequestMapping(value="/api/employee/", method=RequestMethod.POST)
    public void saveEmployee(@RequestBody Employee e){
        try{
            eService.save(e);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @RequestMapping(value="/api/employee/{id}", method=RequestMethod.GET)
    public Employee findById(@PathVariable("id") Integer id){
        return eService.findById(id);
    }

    @RequestMapping(value="/api/employee/", method=RequestMethod.PUT)
    public Employee editEmployee(@RequestBody Employee e){
        try{
            return eService.edit(e);
        }catch(Exception ex){
            ex.printStackTrace();
            return e;
        }
    }

    @RequestMapping(value="/api/employee/person/{id}", method=RequestMethod.GET)
    public Employee findByPersonId(@PathVariable("id") Integer personid){
        return eService.findByPersonId(personid);
    }
}
