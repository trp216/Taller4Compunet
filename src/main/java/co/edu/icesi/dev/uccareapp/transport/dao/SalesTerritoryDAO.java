package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;

@Repository
@Transactional
@Scope("singleton")
public class SalesTerritoryDAO implements ISalesTerritoryDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
//	@Autowired
//	public AddressDAO(EntityManager entityManager) {
//		this.entityManager = entityManager;
//	}
	
	
	//@Transactional
	@Override
	public void save(Salesterritory entity) {
		entityManager.persist(entity);		
		
	}

	
	//@Transactional
	@Override
	public void update(Salesterritory entity) {
		entityManager.merge(entity);		
		
	}

	//@Transactional
	@Override
	public void delete(Salesterritory entity) {
		entityManager.remove(entity);		
		
	}

	
	@Override
	public Salesterritory findById(Integer id) {
		return entityManager.find(Salesterritory.class, id);		//codigo? ID?
	}

	
	@Override
	public List<Salesterritory> findAll() {
		String jpql = "Select st from Salesterritory st";
		TypedQuery<Salesterritory> query = entityManager.createQuery(jpql, Salesterritory.class);
		return 	query.getResultList();	
	}
	
}
