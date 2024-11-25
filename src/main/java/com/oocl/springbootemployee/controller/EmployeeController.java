package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }

    @GetMapping("/{employeeId}")
    public Employee getById(@PathVariable("employeeId") Integer employeeId) {
        return employeeRepository.getById(employeeId);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{employeeId}")
    public boolean deleteById(@PathVariable("employeeId") Integer employeeId) {
        return employeeRepository.deleteById(employeeId);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getByGender(@RequestParam Gender gender) {
        return employeeRepository.getByGender(gender);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeRepository.create(employee);
    }
}
