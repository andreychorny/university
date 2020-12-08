package com.university.repository;

import com.university.entity.Department;
import com.university.entity.Lector;

import java.math.BigDecimal;
import java.util.List;

public interface DepartmentRepository {

    public List<Department> findAll();

    public Department findByName(String departmetName);

    public Lector findHead(String departmentName);

    public BigDecimal findAverageSalary(String departmentName);


}
