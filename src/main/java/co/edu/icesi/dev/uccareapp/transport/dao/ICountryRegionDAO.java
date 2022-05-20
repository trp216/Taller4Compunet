package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;


public interface ICountryRegionDAO {
	
	public Countryregion save(Countryregion entity);
	public Countryregion update(Countryregion entity);
	public void delete(Countryregion entity);
	public Countryregion findById(Integer codigo);
	public List<Countryregion> findAll();

}
