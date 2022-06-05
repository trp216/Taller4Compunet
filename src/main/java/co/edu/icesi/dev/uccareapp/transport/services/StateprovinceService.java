package co.edu.icesi.dev.uccareapp.transport.services;

import co.edu.icesi.dev.uccareapp.transport.exception.ElementNotFoundException;
import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;

public interface StateprovinceService {
	
	public Stateprovince saveStateprovince(Stateprovince stateProvince, Integer salesterritoryid, Integer countryregionid) throws FailedValidationsException, ElementNotFoundException;
	
	public Stateprovince editStateprovince(Stateprovince stateProvince, Integer salesterritoryid, Integer countryregionid) throws FailedValidationsException, ElementNotFoundException;
		
	public Iterable<Stateprovince> findAll();
	
	public Stateprovince findById(Integer id);
	
	public void save(Stateprovince sp);
	
//	public Stateprovince edit(Stateprovince sp, Integer countryregionid);
	public Stateprovince edit(Stateprovince sp);
	
}
