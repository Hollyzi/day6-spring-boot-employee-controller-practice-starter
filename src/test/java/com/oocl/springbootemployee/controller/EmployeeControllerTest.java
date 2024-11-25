package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
//import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class EmployeeControllerTest {
    @Autowired
    private MockMvc client;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    JacksonTester<List<Employee>> jsonList;

    @Autowired
    JacksonTester<Employee> json;

    @BeforeEach
    void setup() {
        employeeRepository.getAll().clear();
        employeeRepository.create(new Employee(1, "name1", 15, Gender.FEMALE, 18.0));
        employeeRepository.create(new Employee(2, "name2", 15, Gender.MALE, 18.0));
        employeeRepository.create(new Employee(3, "name3", 15, Gender.FEMALE, 18.0));
    }

    @Test
    void should_return_employees_when_getAll_given_employeeRepository() throws Exception {
        // Given
        final List<Employee> givenEmployees = employeeRepository.getAll();

        // When
        // Then
        String employeesJsonString = client.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(givenEmployees.size())))
                .andReturn().getResponse().getContentAsString();

        List<Employee> employees = jsonList.parseObject(employeesJsonString);
        assertThat(employees).usingRecursiveComparison().isEqualTo(givenEmployees);
    }

    @Test
    void should_return_employee_when_getById_given_employeeId() throws Exception {
        // Given
        final Employee givenEmployee = employeeRepository.getById(1);

        // When
        // Then
        String employeeJsonString = client.perform(MockMvcRequestBuilders.get("/employees/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        Employee employee = json.parseObject(employeeJsonString);
        assertThat(employee).isEqualTo(givenEmployee);
    }

    @Test
    void should_return_male_employee_when_getByGender_given_male() throws Exception {
        // Given
        final List<Employee> givenEmployees = employeeRepository.getByGender(Gender.MALE);

        // When
        // Then
        String employeesJsonString = client.perform(MockMvcRequestBuilders.get("/employees")
                        .param("gender", "MALE"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(givenEmployees.size())))
                .andReturn().getResponse().getContentAsString();

        List<Employee> employees = jsonList.parseObject(employeesJsonString);
        assertThat(employees).usingRecursiveComparison().isEqualTo(givenEmployees);
    }

    @Test
    void should_return_employee_when_create_employee_given_employee() throws Exception {
        //Given
        String employeeJson = " {\n" +
                "        \"name\": \"name1\",\n" +
                "        \"age\": 15,\n" +
                "        \"gender\": \"FEMALE\",\n" +
                "        \"salary\": 18.0\n" +
                "    }";
        Employee newEmployee = new Employee(4, "name1", 15, Gender.FEMALE, 18.0);

        //When
        //Then
        String employeeJsonString = client.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Employee employee = json.parseObject(employeeJsonString);
        assertThat(employee).usingRecursiveComparison().isEqualTo(newEmployee);
    }

    @Test
    void should_return_true_when_deleteById_given_employeeId() throws Exception {
        //Given
        //When
        client.perform(MockMvcRequestBuilders.delete("/employees/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn().getResponse().getContentAsString();

    }

    @Test
    void should_return_updated_employee_when_update_employee_given_age_and_salary() throws Exception {
        //Given
        String employeeJson = " {\n" +
                "        \"age\": 89,\n" +
                "        \"salary\": 1890.0\n" +
                "    }";
        //When
        //Then
        String employeeJsonString = client.perform(MockMvcRequestBuilders.put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        Employee givenEmployee = employeeRepository.getById(1);
        Employee employee = json.parseObject(employeeJsonString);
        assertThat(employee).usingRecursiveComparison().isEqualTo(givenEmployee);
    }

}