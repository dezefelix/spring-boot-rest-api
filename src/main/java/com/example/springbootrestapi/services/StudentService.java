package com.example.springbootrestapi.services;

import com.example.springbootrestapi.models.Student;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Using JPA with a mix of JPA OO style operations and JPQL queries.
 */
@Service
@Transactional
public class StudentService {
    @PersistenceContext
    private EntityManager em;

    public StudentService() {
    }

    // Although Student table is not recognized. This does work!
    public List<Student> fetch() {
        final String query = "SELECT student FROM Student student";
        return this.em.createQuery(query, Student.class).getResultList();
    }

    // Although Student table is not recognized. This does work!
    public Student fetch(int id) {
        TypedQuery<Student> query = this.em.createQuery(
                "SELECT student FROM Student student WHERE student.id = :id",
                Student.class
        );
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public Student save(Student student) {
        this.em.persist(student);
        return student;
    }

    public Student update(Student student) {
        // Looking an entity up in the database manages the entity.
        // This enables it to be updated and deleted.
        Student managedEntity = this.em.find(Student.class, student.getId());
        if (managedEntity != null) {
            this.em.persist(managedEntity);
            return managedEntity;
        }
        return null;
    }

    public void delete(Student student) {
        Student managedEntity = this.getManagedEntity(student);
        this.em.remove(managedEntity);
    }

    private Student getManagedEntity(Student entity) {
        // Check if the entity is managed by the persistence context.
        // Otherwise, removal wouldn't work.
         return this.em.contains(entity) ? entity : this.em.merge(entity);
    }
}
