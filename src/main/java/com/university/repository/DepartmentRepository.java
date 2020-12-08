package com.university.repository;

import com.university.entity.Department;
import com.university.entity.Lector;

import java.math.BigDecimal;
import java.util.List;

public interface DepartmentRepository {

    List<Department> findAll();

    List<Department> findListByTemplate(String template);

    Department findByName(String departmentName);

    Lector findHead(String departmentName);

    BigDecimal findAverageSalary(String departmentName);


}
