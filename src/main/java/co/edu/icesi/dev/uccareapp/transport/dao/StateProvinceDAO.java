package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;

@Repository
@Transactional
@Scope("singleton")
public class StateProvinceDAO implements IStateProvinceDAO{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Stateprovince save(Stateprovince entity) {
		entityManager.persist(entity);		
		return entity;
	}

	@Override
	public Stateprovince update(Stateprovince entity) {
		entity = entityManager.merge(entity);		
		return entity;
	}

	@Override
	public void delete(Stateprovince entity) {
		entityManager.remove(entity);		

	}

	@Override
	public Stateprovince findById(Integer codigo) {
		return entityManager.find(Stateprovince.class, codigo);		//codigo? ID?
	}

	@Override
	public List<Stateprovince> findAll() {
		String jpql = "Select a from Stateprovince a";
		TypedQuery<Stateprovince> q = entityManager.createQuery(jpql, Stateprovince.class);
		return 	q.getResultList();	
	}

	//Permita que los estados-provincia puedan buscarse 
	//por el id del país-región
	//	public Stateprovince getStateprovinceByCountryregion(Integer id) {
	//		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.countryregion.countryregionid =:id";
	//		Query query = entityManager.createQuery(jpql);
	//		query.setParameter("id", id);
	//		Stateprovince stateprovince = (Stateprovince) query.getSingleResult();
	//		return stateprovince;
	//	}

	public List<Stateprovince> getStateprovinceByCountryregion2(Integer id) {
		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.countryregion.countryregionid = '"+id+"'";
		return entityManager.createQuery(jpql,Stateprovince.class).getResultList();
	}


	//Permita que los estados-provincia puedan buscarse 
	//por el id del territorio
	//	public Stateprovince getStateprovinceByTerritoryId(Integer id) {
	//		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.territoryid =:id";
	//		Query query = entityManager.createQuery(jpql);
	//		query.setParameter("id", id);
	//		Stateprovince stateprovince = (Stateprovince) query.getSingleResult();
	//		return stateprovince;
	//	}

	public List<Stateprovince> getStateprovinceByTerritoryId2(Integer id) {
		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.territoryid = '"+id+"'";
		return entityManager.createQuery(jpql,Stateprovince.class).getResultList();
	}

	//Permita que los estados-provincia puedan buscarse 
	//por el nombre 
	//	public Stateprovince getStateprovinceByName(String name) {
	//		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.name =:name";
	//		Query query = entityManager.createQuery(jpql);
	//		query.setParameter("name", name);
	//		Stateprovince stateprovince = (Stateprovince) query.getSingleResult();
	//		return stateprovince;
	//	}

	public List<Stateprovince> getStateprovinceByName2(String name) {
		String jpql = "SELECT sp FROM Stateprovince sp WHERE sp.name = '"+name+"'";
		return entityManager.createQuery(jpql,Stateprovince.class).getResultList();
	}

	//Mostrar el listado de las direcciones para 
	//las direcciones que tienen al menos dos
	//encabezados de órdenes de venta.
	public List<Address> getAddressesWithSalesorderheader() {
		String jpql = "SELECT ad "
				+ "FROM Address ad, Salesterritory st, Salesorderheader soh, Stateprovince sp "
				+ "WHERE sp.territoryid = st.territoryid "
				+ "AND sp.stateprovinceid = ad.stateprovince "
				+ "AND soh.salesterritory = st.territoryid "
				+ "AND (COUNT(soh.salesorderid))>=2";

		return entityManager.createQuery(jpql,Address.class).getResultList();

			
	}


}
