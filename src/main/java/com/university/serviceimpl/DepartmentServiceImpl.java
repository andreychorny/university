package com.university.serviceimpl;

import com.university.entity.Degree;
import com.university.entity.Department;
import com.university.entity.Lector;
import com.university.repository.DegreeRepository;
import com.university.repository.DepartmentRepository;
import com.university.service.DegreeService;
import com.university.service.DepartmentService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DegreeService degreeService;

    @Override
    @Transactional
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
            System.out.println("Wrong name of a department");
            return null;
        }catch (NullPointerException exception){
            System.out.println("The department doesn't have head yet");
            return null;
        }
        System.out.println("Head of " + departmentName +" is " + head.getName());
        return head;
    }

    @Override
    public List<Department> findListByTemplate(String template) {
        return departmentRepository.findListByTemplate(template);
    }

    @Override
    @Transactional
    public Department findByName(String departmentName) {
        Department department;
        try{
            department = departmentRepository.findByName(departmentName);
        }catch (NoResultException | EmptyResultDataAccessException exception){
            System.out.println("Wrong name of a department");
            return null;
        }
        return department;
    }

    @Override
    @Transactional
    public int findCountOfEmployees(String departmentName) {
        Department department = findByName(departmentName);
        if(department==null) return -1;
        int count = department.getLectors().size();
        System.out.println(count);
        return count;
    }

    @Override
    @Transactional
    public Department showStatistics(String departmentName) {
        Department department = findByName(departmentName);
        if(department == null) return null;
        List<Degree> possibleDegrees = degreeService.findAll();
        StringBuffer[] stringBuffers = new StringBuffer[possibleDegrees.size()];
        int[] countPerDegree = new int[possibleDegrees.size()];
        for(int i=0; i<stringBuffers.length;i++){
            stringBuffers[i] = new StringBuffer();
        }
        for(Lector lector: department.getLectors()){
            int degreeIndex = lector.getDegree().getId()-1;
            stringBuffers[degreeIndex].append(lector.getName() + "; ");
            countPerDegree[degreeIndex]++;
        }
        for(int i=0; i<stringBuffers.length; i++){
            System.out.println(possibleDegrees.get(i).getName() + "s(" + countPerDegree[i]+"): "
                    + stringBuffers[i].toString());
        }
        return department;
    }

    @Override
    @Transactional
    public BigDecimal findAverageSalary(String departmentName) {
        BigDecimal avgSalary;
        try{
            avgSalary = departmentRepository.findAverageSalary(departmentName);
        }catch (NoResultException | EmptyResultDataAccessException exception){
            System.out.println("Wrong name of a department or the department has no employees yet");
            return null;
        }
        System.out.println("The average salary of " + departmentName + " is " +
                avgSalary);
        return avgSalary;
    }

}
