package com.university.repository;

import com.university.entity.Degree;

import java.util.List;

public interface DegreeRepository {

    List<Degree> findAll();

    List<Degree> findListByTemplate(String template);
}
