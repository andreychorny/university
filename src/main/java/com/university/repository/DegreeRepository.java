package com.university.repository;

import com.university.entity.Degree;

import java.util.List;

public interface DegreeRepository {

    public List<Degree> findAll();
}
