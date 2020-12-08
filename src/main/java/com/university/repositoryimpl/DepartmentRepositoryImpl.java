package com.university.repositoryimpl;

import com.university.entity.Department;
import com.university.entity.Lector;
import com.university.repository.DepartmentRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Department> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Department> query =
                currentSession.createQuery("from Department", Department.class);
        List<Department> departments = query.getResultList();
        return departments;
    }

    @Override
    public Lector findHead(String departmentName) throws NoResultException {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("SELECT d.head FROM Department d WHERE d.name= " +
                ":departmentName");
        query.setParameter("departmentName",  departmentName);
        Lector head = (Lector)query.getSingleResult();
        return head;
    }
}
