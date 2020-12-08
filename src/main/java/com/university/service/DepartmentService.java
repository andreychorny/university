package com.university.service;

import com.university.entity.Department;
import com.university.entity.Lector;

import java.math.BigDecimal;
import java.util.List;

public interface DepartmentService {

    public List<Department> findAll();

    public Lector findHead(String departmentName);

    public Department showStatistics(String departmentName);

    public BigDecimal findAverageSalary(String departmentName);
}
