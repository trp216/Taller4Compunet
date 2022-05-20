package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;

public interface ISalesTerritoryDAO {

	//@Transactional
	void save(Salesterritory entity);

	//@Transactional
	void update(Salesterritory entity);

	//@Transactional
	void delete(Salesterritory entity);

	Salesterritory findById(Integer id);

	List<Salesterritory> findAll();

}