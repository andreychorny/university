package com.university;

import com.university.entity.Degree;
import com.university.entity.Department;
import com.university.entity.Lector;
import com.university.service.DegreeService;
import com.university.service.DepartmentService;
import com.university.service.LectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsoleController {

    @Autowired
    private DegreeService degreeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LectorService lectorService;

    public void findByTemplate(String template){
        StringBuilder stringBuilder = new StringBuilder();
        for(Lector lector: lectorService.findListByTemplate(template)){
            stringBuilder.append(lector.getName() + ", ");
        }
        for(Department department: departmentService.findListByTemplate(template)){
            stringBuilder.append(department.getName() + ", ");
        }
        for(Degree degree: degreeService.findListByTemplate(template)){
            stringBuilder.append(degree.getName() + ", ");
        }
        stringBuilder.setLength(stringBuilder.length()-2);
        System.out.println(stringBuilder.toString());
    }
}
