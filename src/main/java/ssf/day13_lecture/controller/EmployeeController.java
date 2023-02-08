package ssf.day13_lecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import ssf.day13_lecture.repository.EmployeeRepo;
import ssf.day13_lecture.model.Employee;


@Controller
@RequestMapping(path = "/employees")
public class EmployeeController {
    
    @Autowired
    EmployeeRepo empRepo;

    @GetMapping("/home")
    public String employeeListPage(Model model) {
        List<Employee> employees = empRepo.findAll();
        model.addAttribute("employees", employees);
        return "employeelist";
    }

    @GetMapping("/addnew")
    public String addPage(Model model) {
        Employee emp = new Employee();
        model.addAttribute("employee", emp);
        return "employeeadd";
    }

    // @PostMapping("/addnew")
    // public String addEmployee(@ModelAttribute("employee") Employee employeeForm, Model 
    // model, BindingResult result) {

    //     if (result.hasErrors()) {
    //         return "employeeadd";
    //     }

    //     Boolean bresult = false;
    //     bresult = empRepo.save(employeeForm);
        
    //     return "redirect:/home";
    // }

}
