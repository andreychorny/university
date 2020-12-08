package com.university.service;

import com.university.entity.Department;
import com.university.entity.Lector;

import java.util.List;

public interface DepartmentService {

    public List<Department> findAll();

    public Lector findHead(String departmentName);
}
