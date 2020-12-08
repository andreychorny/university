package com.university.serviceimpl;

import com.university.entity.Degree;
import com.university.repository.DegreeRepository;
import com.university.service.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DegreeServiceImpl implements DegreeService {

    @Autowired
    private DegreeRepository degreeRepository;

    @Override
    @Transactional
    public List<Degree> findAll() {
        return degreeRepository.findAll();
    }

    @Override
    @Transactional
    public List<Degree> findListByTemplate(String template) {
        return degreeRepository.findListByTemplate(template);
    }
}
