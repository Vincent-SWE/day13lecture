package ssf.day13_lecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

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

    @PostMapping("/addnew")
    // BindingResult must come before Model
    // The order sequence matters!
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employeeForm, 
    BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "employeeadd";
        }

        Boolean bresult = false;
        bresult = empRepo.save(employeeForm);
        
        return "redirect:/employees/home";
    }


    @GetMapping("/deleteEmployee/{email}")
    public String delEmployee(@PathVariable("email") String email) {

        Employee emp = empRepo.findByEmailId(email);

        Boolean bResult = empRepo.delete(emp);


        return "redirect:/employees/home";
    }




}
