package com.university;

import com.university.repository.DepartmentRepository;
import com.university.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UniversityApplication implements CommandLineRunner {


	@Autowired
	private DepartmentService departmentService;

	public static void main(String[] args) {

		SpringApplication.run(UniversityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		departmentService.findHead("NU_LP2");
		departmentService.showStatistics("NU_LP");
		departmentService.findAverageSalary("NU_LP");
		departmentService.findCountOfEmployees("NU_LP");
	}
}
