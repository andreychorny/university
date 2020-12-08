package com.university.repositoryimpl;

import com.university.entity.Degree;
import com.university.entity.Department;
import com.university.repository.DegreeRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DegreeRepositoryImpl implements DegreeRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Degree> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Degree> query =
                currentSession.createQuery("from Degree", Degree.class);
        List<Degree> degrees = query.getResultList();
        return degrees;
    }
}
