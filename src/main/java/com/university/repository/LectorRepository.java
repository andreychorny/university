package com.university.repository;

import com.university.entity.Lector;

import java.util.List;

public interface LectorRepository {

    List<Lector> findAll();

    List<Lector> findListByTemplate(String template);

}
