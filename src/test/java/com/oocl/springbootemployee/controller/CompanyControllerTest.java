package com.oocl.springbootemployee.controller;


import com.oocl.springbootemployee.model.Company;
import com.oocl.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
public class CompanyControllerTest {
    @Autowired
    private MockMvc client;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    JacksonTester<List<Company>> jsonList;

    @Test
    void should_return_all_companies_when_getAll_given_companyRepository() throws Exception {
        //Given
        List<Company> givenCompanies = companyRepository.getAll();
        //When
        String companyJsonString = client.perform(MockMvcRequestBuilders.get("/companies")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(givenCompanies.size()))).andReturn().getResponse().getContentAsString();
        //Then
        List<Company> companies = jsonList.parseObject(companyJsonString);
        assertThat(companies).usingRecursiveComparison().isEqualTo(givenCompanies);
    }
}
