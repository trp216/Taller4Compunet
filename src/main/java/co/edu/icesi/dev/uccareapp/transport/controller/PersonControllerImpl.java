package co.edu.icesi.dev.uccareapp.transport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.dev.uccareapp.transport.delegate.PersonDelegate;
import co.edu.icesi.dev.uccareapp.transport.model.person.Person;

@Controller
public class PersonControllerImpl {

    private PersonDelegate pDelegate;

    @Autowired
    public PersonControllerImpl(PersonDelegate d){
        pDelegate = d;
    }
    
    @GetMapping("/person/")
    public String indexPersons(Model model) {
        model.addAttribute("persons", pDelegate.findAll());
        return "person/index";
    }

    @GetMapping("/person/add")
    public String addPerson(Model model) {
        model.addAttribute("person", new Person());
        return "person/add-person";
    }

    @PostMapping("/person/add")
    public String savePerson(Person p, BindingResult bindingResult, Model model, @RequestParam(value="action", required = true)String action){
        if(!action.equals("Cancel")){
            model.addAttribute("person", p);
            if(bindingResult.hasErrors()){
                return "/person/add-person";
            }
            pDelegate.save(p);
        }
        return "redirect:/person/";
    }

    @GetMapping("/person/edit/{id}")
    public String showEditPerson(@PathVariable("id") Integer id, Model model){
        Person person = pDelegate.findById(id);
        if(person == null)
            throw new IllegalArgumentException("Invalid Person ID. How did you even trigger this?");

        model.addAttribute("person", person);
        return "person/edit-person";
    }
}
