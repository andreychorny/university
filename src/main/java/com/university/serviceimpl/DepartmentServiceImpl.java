package com.university.serviceimpl;

import com.university.entity.Degree;
import com.university.entity.Department;
import com.university.entity.Lector;
import com.university.repository.DegreeRepository;
import com.university.repository.DepartmentRepository;
import com.university.service.DepartmentService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DegreeRepository degreeRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional
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

    @Override
    @Transactional
    public Department showStatistics(String departmentName) {
        Department department;
        try{
            department = departmentRepository.findByName(departmentName);
        }catch (NoResultException | EmptyResultDataAccessException exception){
            System.out.println("Wrong name of department");
            return null;
        }
        List<Degree> possibleDegrees = degreeRepository.findAll();
        StringBuffer[] stringBuffers = new StringBuffer[possibleDegrees.size()];
        int[] countPerDegree = new int[possibleDegrees.size()];
        for(Lector lector: department.getLectors()){
            int degreeIndex = lector.getDegree().getId()-1;
            if(stringBuffers[degreeIndex]==null) stringBuffers[degreeIndex] = new StringBuffer();
            stringBuffers[degreeIndex].append(lector.getName() + "; ");
            countPerDegree[degreeIndex]++;
        }
        for(int i=0; i<stringBuffers.length; i++){
            System.out.println(possibleDegrees.get(i).getName() + "s(" + countPerDegree[i]+"): "
                    + stringBuffers[i].toString());
        }
        return department;
    }

}
