package com.university.repositoryimpl;

import com.university.entity.Lector;
import com.university.repository.LectorRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LectorRepositoryImpl implements LectorRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Lector> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Lector> query =
                currentSession.createQuery("from Lector", Lector.class);
        List<Lector> lectors = query.getResultList();
        return lectors;
    }
}
