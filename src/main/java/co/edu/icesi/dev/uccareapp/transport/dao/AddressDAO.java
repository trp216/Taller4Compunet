package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;

@Repository
@Transactional
@Scope("singleton")
public class AddressDAO implements IAddressDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
//	@Autowired
//	public AddressDAO(EntityManager entityManager) {
//		this.entityManager = entityManager;
//	}
	
	@Override
	//@Transactional
	public Address save(Address entity) {
		entityManager.persist(entity);		
		return entity;
	}

	@Override
	//@Transactional
	public Address update(Address entity) {
		entityManager.merge(entity);		
		return entity;
	}

	@Override
	//@Transactional
	public void delete(Address entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public Address findById(Integer code) {
		return entityManager.find(Address.class, code);		//codigo? ID?
	}

	@Override
	public List<Address> findAll() {
		String jpql = "Select a from Address a";
		TypedQuery<Address> ret = entityManager.createQuery(jpql, Address.class);
		return 	ret.getResultList();	
	}
	
	//Permita que los direcciones puedan 
	//buscarse por la ciudad 
	
//	public List<Address> getAddressByCity(String city) {
//		String jpql = "SELECT a FROM Address a WHERE a.city =:city";
//		Query query = entityManager.createQuery(jpql);
//		query.setParameter("city", city);
////		Address address = (Address) query.getSingleResult();
////		return address;
//		return entityManager.createQuery(jpql,Address.class).getResultList();
//	}
	
	public List<Address> getAddressByCity2(String city) {
		String jpql = "SELECT a FROM Address a WHERE a.city = '" + city + "'";
		return entityManager.createQuery(jpql,Address.class).getResultList();
	}
	
	//Permita que los direcciones puedan 
	//buscarse por el id del estado-provincia
//	public Address getAddressByStateprovince(Integer id) {
//		String jpql = "SELECT a FROM Address a WHERE a.stateprovince.stateprovinceid =:id";
//		Query query = entityManager.createQuery(jpql);
//		query.setParameter("id", id);
//		Address address = (Address) query.getSingleResult();
//		return address;
//	}
	
	public List<Address> getAddressByStateprovince2(Integer id) {
		String jpql = "SELECT a FROM Address a WHERE a.stateprovince.stateprovinceid = '" +id +"'";
		return entityManager.createQuery(jpql,Address.class).getResultList();
	}
	
	//Mostrar el listado de las direcciones para 
	//las direcciones que tienen al menos dos
	//encabezados de órdenes de venta.
//	public List<Address> getAddressAtLeast2Headers() {
//		String jpql = "SELECT a FROM Address a WHERE a.stateprovince.stateprovinceid = '" +id +"'";
//		return entityManager.createQuery(jpql,Address.class).getResultList();
//	}
}
