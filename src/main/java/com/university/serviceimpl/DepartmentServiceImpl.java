package com.university.serviceimpl;

import com.university.entity.Department;
import com.university.entity.Lector;
import com.university.repository.DepartmentRepository;
import com.university.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Lector findHead(String departmentName) {
        Lector head;
        try{
            head = departmentRepository.findHead(departmentName);
        }catch (NoResultException exception){
            System.out.println("Wrong name of department");
            return null;
        }
        System.out.println("Head of " + departmentName +" is " + head.getName());
        return head;
    }
}
