package com.university.repositoryimpl;

import com.university.entity.Degree;
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
    public List<Lector> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Lector> query =
                currentSession.createQuery("from Lector", Lector.class);
        List<Lector> lectors = query.getResultList();
        return lectors;
    }

    @Override
    public List<Lector> findListByTemplate(String template) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Lector> query = currentSession.createQuery("SELECT l FROM Lector l WHERE l.name LIKE " +
                " :template");
        query.setParameter("template", "%" + template + "%");
        List<Lector> lectors = query.list();
        return lectors;
    }
}
