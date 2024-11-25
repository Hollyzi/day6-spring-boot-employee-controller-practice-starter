package com.oocl.springbootemployee.repository;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    List<Employee> employees = new ArrayList<>();

    EmployeeRepository() {
        employees.add(new Employee(1, "name1", 15, Gender.FEMALE, 18.0));
        employees.add(new Employee(2, "name2", 15, Gender.MALE, 18.0));
        employees.add(new Employee(3, "name3", 15, Gender.FEMALE, 18.0));
    }

    public List<Employee> getAll() {
        return employees;
    }

    public Employee getById(Integer employeeId) {
        return employees.stream().filter(employee -> employee.getId() == employeeId).findFirst().orElse(null);
    }

    public boolean deleteById(Integer employeeId) {
        return employees.removeIf(employee -> employee.getId() == employeeId);
    }

    public List<Employee> getByGender(Gender gender) {
        return employees.stream()
                .filter(employee -> employee.getGender() == gender).collect(Collectors.toList());
    }

    public Employee create(Employee employee) {
        employee.setId(employees.size() + 1);
        employees.add(employee);
        return employee;
    }

    public Employee update(Integer employeeId, Employee employee) {
        Employee getEmployeeById = getById(employeeId);
        getEmployeeById.setAge(employee.getAge());
        getEmployeeById.setSalary(employee.getSalary());
        return getEmployeeById;
    }
}
