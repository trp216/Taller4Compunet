package co.edu.icesi.dev.uccareapp.transport.services;

import co.edu.icesi.dev.uccareapp.transport.exception.ElementNotFoundException;
import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;

public interface SalestaxrateService {
	
	public Salestaxrate saveSalestaxrate(Salestaxrate str, int stateprovinceid) throws FailedValidationsException, ElementNotFoundException;
	
	public Salestaxrate editSalestaxrate(Salestaxrate str, int stateprovinceid) throws FailedValidationsException, ElementNotFoundException;

	public Iterable<Salestaxrate> findAll();
	
	public void save(Salestaxrate str);
}
