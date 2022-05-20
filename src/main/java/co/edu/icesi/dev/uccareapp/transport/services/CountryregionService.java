package co.edu.icesi.dev.uccareapp.transport.services;

import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;

public interface CountryregionService {
	
	public Countryregion saveCountryRegion(Countryregion cr) throws FailedValidationsException;
	
	public Countryregion editCountryRegion(Countryregion cr) throws FailedValidationsException;
	
	public Iterable<Countryregion> findAll();
	
	public void save(Countryregion cr);

	Countryregion findById(Integer id);
}
