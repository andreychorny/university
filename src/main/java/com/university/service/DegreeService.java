package com.university.service;

import com.university.entity.Degree;

import java.util.List;

public interface DegreeService {
    List<Degree> findAll();

    List<Degree> findListByTemplate(String template);

}
