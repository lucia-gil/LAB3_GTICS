package org.example.lab3Gtics.controller;

import com.example.lab3Gtics.model.Employee;
import com.example.lab3Gtics.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public String listado(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employee/listado";
    }

    @GetMapping("/ver")
    public String ver(@RequestParam("id") Integer id, Model model) {
        Optional<Employee> resultado = employeeRepository.findById(id);
        if (resultado.isPresent()) {
            model.addAttribute("employee", resultado.get());
            return "employee/detalle";
        }
        return "redirect:/employees";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(value = "texto", required = false) String texto,
                         @RequestParam(value = "puesto", required = false) String puesto,
                         @RequestParam(value = "salarioMinimo", required = false) BigDecimal salarioMinimo,
                         Model model) {

        List<Employee> resultado = employeeRepository.findAll().stream()
                .filter(e -> texto == null || texto.isBlank()
                        || (e.getFirstName() != null && e.getFirstName().toLowerCase().contains(texto.toLowerCase()))
                        || (e.getLastName() != null && e.getLastName().toLowerCase().contains(texto.toLowerCase()))
                        || (e.getEmail() != null && e.getEmail().toLowerCase().contains(texto.toLowerCase())))
                .filter(e -> puesto == null || puesto.isBlank()
                        || (e.getJobId() != null && e.getJobId().equalsIgnoreCase(puesto)))
                .filter(e -> salarioMinimo == null
                        || (e.getSalary() != null && e.getSalary().compareTo(salarioMinimo) >= 0))
                .collect(Collectors.toList());

        model.addAttribute("employees", resultado);
        return "employee/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/registro";
    }

    @PostMapping("/guardar")
    public String guardar(Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        Optional<Employee> resultado = employeeRepository.findById(id);
        if (resultado.isPresent()) {
            model.addAttribute("employee", resultado.get());
            return "employee/editar";
        }
        return "redirect:/employees";
    }

}