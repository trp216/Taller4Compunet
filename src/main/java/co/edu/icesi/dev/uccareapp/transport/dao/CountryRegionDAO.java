package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;

@Repository
@Transactional
@Scope("singleton")
public class CountryRegionDAO implements ICountryRegionDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Countryregion save(Countryregion entity) {
		entityManager.persist(entity);		
		return entity;
		
	}

	@Override
	public Countryregion update(Countryregion entity) {
		entityManager.merge(entity);		
		return entity;
	}

	@Override
	public void delete(Countryregion entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public Countryregion findById(Integer codigo) {
		return entityManager.find(Countryregion.class, codigo);		//codigo? ID?
	}

	@Override
	public List<Countryregion> findAll() {
		String jpql = "Select a from Countryregion a";
		TypedQuery<Countryregion> ret = entityManager.createQuery(jpql, Countryregion.class);
		return 	ret.getResultList();	
	}
	
}
