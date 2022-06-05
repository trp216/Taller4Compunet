package co.edu.icesi.dev.uccareapp.transport.services;

import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;
import co.edu.icesi.dev.uccareapp.transport.exception.ElementNotFoundException;
import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;

public interface SalestaxrateService {
	
	public Salestaxrate saveSalestaxrate(Salestaxrate str, int stateprovinceid) throws FailedValidationsException, ElementNotFoundException;
	
	public Salestaxrate editSalestaxrate(Salestaxrate str, int stateprovinceid) throws FailedValidationsException, ElementNotFoundException;
	
	public Salestaxrate edit(Salestaxrate str, Integer stateprovinceid);
	
	public Salestaxrate findById(Integer id);

	public Iterable<Salestaxrate> findAll();
	
	public Salestaxrate save(Salestaxrate str);
}
