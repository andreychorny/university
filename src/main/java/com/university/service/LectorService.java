package com.university.service;

import com.university.entity.Lector;

import java.util.List;

public interface LectorService {

    List<Lector> findAll();

    List<Lector> findListByTemplate(String template);

}
