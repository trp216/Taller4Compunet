package co.edu.icesi.dev.uccareapp.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.uccareapp.transport.model.person.Person;
import co.edu.icesi.dev.uccareapp.transport.services.PersonServiceImp;

@RestController
public class PersonRestController {
    
    @Autowired
    private PersonServiceImp pService;

    @RequestMapping(value = "/api/person/", method = RequestMethod.GET)
    public Iterable<Person> findAll(){
        return pService.findAll();
    }

    @RequestMapping(value= "/api/person", method=RequestMethod.POST)
    public void savePerson(@RequestBody Person person){
        try{
            pService.save(person);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value= "/api/person/{id}", method = RequestMethod.GET)
    public Person findById(@PathVariable("id") Integer id){
        return pService.findById(id);
    }

    @RequestMapping(value="/api/person", method = RequestMethod.PUT)
    public Person editPerson(@RequestBody Person person){
        try{
            return pService.update(person);
        }catch(Exception e){
            e.printStackTrace();
            return person;
        }
    }
}
