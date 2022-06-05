package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;

@Repository
@Transactional
@Scope("singleton")
public class EmployeeDAO implements IEmployeeDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Employee entity) {
        em.persist(entity);
    }

    @Override
    public Employee update(Employee entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(Employee entity) {
        em.remove(entity);
    }

    @Override
    public Employee findById(Integer id) {
        return em.find(Employee.class, id);
    }

    @Override
    public List<Employee> findAll() {
        String jpql = "SELECT e FROM Employee e";
        TypedQuery<Employee> q = em.createQuery(jpql, Employee.class);
        return q.getResultList();
    }
    
    @Override
    public Employee findByPersonId(Integer personId){
        String jpql = "Select e from Employee e Where e.personid = "+personId;
        TypedQuery<Employee> ret = em.createQuery(jpql, Employee.class);
        if(ret.getResultList().isEmpty()){
            return null;
        }
        return ret.getResultList().get(0);
    }
}
