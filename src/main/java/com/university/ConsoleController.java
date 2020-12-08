package com.university;

import com.university.entity.Degree;
import com.university.entity.Department;
import com.university.entity.Lector;
import com.university.service.DegreeService;
import com.university.service.DepartmentService;
import com.university.service.LectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ConsoleController {

    @Autowired
    private DegreeService degreeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LectorService lectorService;

    public void start() throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Consumer<String>> regexMap = initializeAllRegexCommands();
        endlessConsole:
        while(true){
            System.out.println("Write a command:");
            String line = reader.readLine().trim();
            for(Map.Entry<String, Consumer<String>> entry: regexMap.entrySet()){
                String regex = entry.getKey();
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);
                if(matcher.matches()){
                    entry.getValue().accept(matcher.group(1));
                    continue endlessConsole;
                }
            }
            System.out.println("Wrong command. Try to type 'help' for assistance");
        }
    }

    //All console commands are hard-codded as map of regex, and Consumer-calling of their related methods
    //Pay attention: actual user input ALWAYS should be in the match group, preferably first, and
    //this match group is consumed
    public HashMap<String, Consumer<String>> initializeAllRegexCommands(){
        HashMap<String, Consumer<String>> regexes = new HashMap<>();
        regexes.put("Who is head of department +(.+)", (s) -> findHeadOfDepartment(s));
        regexes.put("Show (.+) * statistics", (s) -> showDepartmentStatistics(s));
        regexes.put("Show the average salary for the department +(.+)", (s) -> findAvgSalary(s));
        regexes.put("Show count of employee for +(.+)", (s) -> findCountOfEmployee(s));
        regexes.put("Global search by +(.+)", (s)->findByTemplate(s));
        regexes.put("^(help)$", (s) -> showHelp());
        return regexes;
    }
    public void showDepartmentStatistics(String departmentName){
        departmentService.showStatistics(departmentName);
    }

    public void findAvgSalary(String departmentName){
        departmentService.findAverageSalary(departmentName);
    }

    public void findHeadOfDepartment(String departmentName){
        departmentService.findHead(departmentName);
    }

    public void findCountOfEmployee(String departmentName){
        departmentService.findCountOfEmployees(departmentName);
    }

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
        if(stringBuilder.length()>=2){stringBuilder.setLength(stringBuilder.length()-2);}
        else{
            stringBuilder.append("NO RESULTS FOUND");
        }
        System.out.println(stringBuilder.toString());
    }

    private void showHelp(){
        System.out.println("notice: You need to type commands exactly word-to-word so they may execute.");
        System.out.println("  'Who is head of department {department_name}'  - shows name of the head of department");
        System.out.println("  'Show {department_name} statistics.'   -shows full statistic of entered department");
        System.out.println("  'Show the average salary for the department {department_name}.'   -shows average salary");
        System.out.println("  'Show count of employee for {department_name}.'   - shows amount of employees in department");
        System.out.println("   'Global search by {template}'   - global search of everything in database by template");
    }
}
