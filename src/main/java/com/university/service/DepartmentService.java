package com.university.service;

import com.university.entity.Department;
import com.university.entity.Lector;

import java.math.BigDecimal;
import java.util.List;

public interface DepartmentService {

    List<Department> findAll();

    Lector findHead(String departmentName);

    List<Department> findListByTemplate(String template);

    Department findByName(String departmentName);

    int findCountOfEmployees(String departmentName);

    Department showStatistics(String departmentName);

    BigDecimal findAverageSalary(String departmentName);
}
