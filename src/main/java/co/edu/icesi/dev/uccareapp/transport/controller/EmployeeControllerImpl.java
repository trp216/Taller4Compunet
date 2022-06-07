package co.edu.icesi.dev.uccareapp.transport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.dev.uccareapp.transport.delegate.EmployeeDelegate;
import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;

@Controller
public class EmployeeControllerImpl {

    private EmployeeDelegate eDelegate;

    @Autowired
    public EmployeeControllerImpl(EmployeeDelegate del) {
        eDelegate = del;
    }

    @GetMapping("/employee/")
    public String indexEmployee(Model model) {
        model.addAttribute("employees", eDelegate.findAll());
        return "employee/index";
    }

    @GetMapping("/employee/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/add-employee";
    }

    @PostMapping("/employee/add")
    public String saveEmployee(Employee e, BindingResult result, Model model,
            @RequestParam(value = "action", required = true) String action) {
        if (!action.equals("Cancel")) {
            model.addAttribute("employee", e);
            if (result.hasErrors()) {
                return "/employee/add-employee";
            }
            eDelegate.save(e);
        }
        return "redirect:/employee/";
    }

    @GetMapping("/employee/edit/{id}")
    public String showEditEmployee(@PathVariable("id")Integer id, Model model){
        Employee e = eDelegate.findById(id);
        if(e == null)
            throw new IllegalArgumentException("You shouldn't be here");
        
        model.addAttribute("employee", e);
        return "employee/edit-employee";
    }

    @PostMapping("/employee/edit/{id}")
    public String editEmployee(@PathVariable("id") Integer id, @RequestParam(value = "action")
    String action, Employee employee, BindingResult result, Model model){
        if(action.equals("Cancel")){
            return "redirect:/employee/";
        }
        if(result.hasErrors()){
             model.addAttribute("employees", eDelegate.findAll());
             return "redirect:/employee/";
        }
        if(action!=null && !action.equals("Cancel")){
            employee.setBusinessentityid(id);
            eDelegate.editEmployee(employee);
        }
        model.addAttribute("employees", eDelegate.findAll());
        return "redirect:/employee/";
    }
}
