package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.person.Person;

@Repository
@Transactional
@Scope("singleton")
public class PersonDAO implements IPersonDAO{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Person entity) {
        em.persist(entity);
    }

    @Override
    public Person update(Person entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(Person entity) {
        em.remove(entity);
    }

    @Override
    public Person findById(Integer id) {
        return em.find(Person.class, id);
    }

    @Override
    public List<Person> findAll() {
        String jpql = "Select p from Person p";
        TypedQuery<Person> ret = em.createQuery(jpql, Person.class);
        return ret.getResultList();
    }
    
    public Person findByEmployeeId(Integer employeeID){
        String jpql = "Select p from Person p Where p.employee.businessentityid = "+employeeID;
        TypedQuery<Person> ret = em.createQuery(jpql, Person.class);
        if(ret.getResultList().isEmpty()){
            return null;
        }
        return ret.getResultList().get(0);
    }
}
