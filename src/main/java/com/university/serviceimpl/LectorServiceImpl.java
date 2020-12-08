package com.university.serviceimpl;

import com.university.entity.Lector;
import com.university.repository.LectorRepository;
import com.university.service.LectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LectorServiceImpl implements LectorService {

    @Autowired
    private LectorRepository lectorRepository;

    @Override
    @Transactional
    public List<Lector> findAll() {
        return lectorRepository.findAll();
    }

    @Override
    @Transactional
    public List<Lector> findListByTemplate(String template) {
        return lectorRepository.findListByTemplate(template);
    }
}
