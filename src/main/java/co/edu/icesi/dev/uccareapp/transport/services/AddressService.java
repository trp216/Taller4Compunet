package co.edu.icesi.dev.uccareapp.transport.services;

import co.edu.icesi.dev.uccareapp.transport.exception.ElementNotFoundException;
import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;

public interface AddressService {
	
public Address saveAddress(int i, Address address) throws FailedValidationsException, ElementNotFoundException;
	
	public Address editAddress(int i, Address address) throws FailedValidationsException, ElementNotFoundException;

	public Iterable<Address> findAll();
	
	public void save(Address address);

}
