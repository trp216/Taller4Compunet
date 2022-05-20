package co.edu.icesi.dev.uccareapp.transport.services;

import co.edu.icesi.dev.uccareapp.transport.exception.ElementNotFoundException;
import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;

public interface StateprovinceService {
	
	public Stateprovince saveStateprovince(Stateprovince stateProvince, Integer salesterritoryid, Integer countryregionid) throws FailedValidationsException, ElementNotFoundException;
	
	public Stateprovince editStateprovince(Stateprovince stateProvince, Integer salesterritoryid, Integer countryregionid) throws FailedValidationsException, ElementNotFoundException;
		
	public Iterable<Stateprovince> findAll();
	
	
	
	public void save(Stateprovince sp);
	
}
