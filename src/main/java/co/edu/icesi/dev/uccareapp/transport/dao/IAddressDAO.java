package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;


public interface IAddressDAO {
	
	public Address save(Address entity);
	public Address update(Address entity);
	public void delete(Address entity);
	public Address findById(Integer codigo);
	public List<Address> findAll();


}
