package com.oocl.springbootemployee.repository;

import com.oocl.springbootemployee.model.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {
    List<Company> Companies = new ArrayList<>();

    public List<Company> getAll() {
        return Companies;
    }

    public CompanyRepository() {
        Companies.add(new Company(1, "Company1"));
        Companies.add(new Company(2, "Company2"));
    }
}
