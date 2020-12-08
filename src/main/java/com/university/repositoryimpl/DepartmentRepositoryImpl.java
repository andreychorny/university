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
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Department> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Department> query =
                currentSession.createQuery("from Department", Department.class);
        List<Department> departments = query.getResultList();
        return departments;
    }

    @Override
    public List<Department> findListByTemplate(String template) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Department> query = currentSession.createQuery("SELECT d FROM Department d WHERE d.name LIKE " +
                " :template");
        query.setParameter("template", "%" + template + "%");
        List<Department> departments = query.list();
        return departments;
    }

    @Override
    public Department findByName(String departmentName) throws NoResultException {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Department> query = currentSession.createQuery("SELECT d FROM Department d WHERE d.name= " +
                ":departmentName");
        query.setParameter("departmentName",  departmentName);
        Department department = (Department) query.getSingleResult();
        return department;
    }

    @Override
    public Lector findHead(String departmentName) throws NoResultException {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Lector> query = currentSession.createQuery("SELECT d.head FROM Department d WHERE d.name= " +
                ":departmentName");
        query.setParameter("departmentName",  departmentName);
        Lector head = query.getSingleResult();
        return head;
    }

    @Override
    public BigDecimal findAverageSalary(String departmentName) throws NoResultException {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("SELECT AVG(l.salary) FROM Department d " +
                "JOIN d.lectors l WHERE d.name= :departmentName");
        query.setParameter("departmentName",  departmentName);
        try{
            BigDecimal avgSalary = BigDecimal.valueOf((double)query.getSingleResult());
            return avgSalary;
        }catch (NullPointerException nullPointerException){
            throw new NoResultException("no result after search - wrong data");
        }
    }
}
