package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesorderheader;

@Repository
@Transactional
@Scope("singleton")
public class SalesOrderHeaderDAO implements ISalesOrderHeaderDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Salesorderheader entity) {
        em.persist(entity);;
    }

    @Override
    public Salesorderheader update(Salesorderheader entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(Salesorderheader entity) {
        em.remove(entity);
    }

    @Override
    public Salesorderheader findById(Integer id) {
        return em.find(Salesorderheader.class, id);
    }

    @Override
    public List<Salesorderheader> findAll() {
        String jpql = "Select soh from Salesorderheader soh";
        TypedQuery<Salesorderheader> query = em.createQuery(jpql, Salesorderheader.class);
        return query.getResultList();
    }
    
}
