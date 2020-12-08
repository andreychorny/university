package com.university.repository;

import com.university.entity.Department;
import com.university.entity.Lector;

import java.util.List;

public interface DepartmentRepository {

    public List<Department> findAll();

    public Lector findHead(String departmentName);

}
