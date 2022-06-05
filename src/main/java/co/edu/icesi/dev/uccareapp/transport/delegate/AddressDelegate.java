package co.edu.icesi.dev.uccareapp.transport.delegate;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;

public interface AddressDelegate {
	
	public Address editAddress(Address address);
	
	public Iterable<Address> findAll();
	
	public void save(Address address);

	public Address findById(Integer id);

}
