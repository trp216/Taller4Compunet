package co.edu.icesi.dev.uccareapp.transport.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.AddressDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.StateProvinceDAO;
import co.edu.icesi.dev.uccareapp.transport.exception.ElementNotFoundException;
import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;
import co.edu.icesi.dev.uccareapp.transport.repositories.AddressRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.StateprovinceRepository;

@Service
public class AddressServiceImp implements AddressService{
	
	//private AddressRepository repo;
	
	//private StateprovinceRepository spRepo;
	
	@Autowired
	private AddressDAO addressDAO;
	
	@Autowired
	private StateProvinceDAO spDAO;
	
	@Autowired
	public AddressServiceImp(AddressDAO addressDAO, StateProvinceDAO spDAO) {
		super();
		this.addressDAO = addressDAO;
		this.spDAO = spDAO;
	}
	
//	public AddressServiceImp(AddressRepository repo, StateprovinceRepository spRepo) {
//		super();
//		this.repo = repo;
//		this.spRepo = spRepo;
//	}

	@Override
	public Address saveAddress(int stateProvinceId, Address address) throws FailedValidationsException, ElementNotFoundException {
		Address result = null;
		
		if(address.getAddressline1()==null || address.getAddressline1().isEmpty() || address.getAddressline1().isBlank()) {
			throw new FailedValidationsException("La linea 1 de direccion no debe estar vacia");
		}
		else if(address.getCity().length()<3) {
			throw new FailedValidationsException("El nombre de la ciudad debe tener al menos 3 caracteres");
		}
		else if(address.getPostalcode().length()!=6) {
			throw new FailedValidationsException("El codigo postal debe tener seis digitos");
		}
		else {
			//Optional<Stateprovince> opt1 = this.spRepo.findById(stateProvinceId);
			Stateprovince opt1 = spDAO.findById(stateProvinceId);
			if(opt1!=null) {
				address.setStateprovince(opt1);
				//result = this.repo.save(address);
				result = addressDAO.save(address);
			}
			else {
				throw new ElementNotFoundException("El estado-provincia no existe");
			}
		}
		
		return result;
	}

	

	@Override
	@Transactional
	public Address editAddress(int stateProvinceId, Address address) throws FailedValidationsException, ElementNotFoundException{
		Address result = null;
		
		if(address.getAddressid()!=null) {
//			Optional<Address> old = repo.findById(address.getAddressid());
			Address old = addressDAO.findById(address.getAddressid());
			if(old!=null) {
				//result = saveAddress(stateProvinceId, address);
				result = addressDAO.update(address);
			}
			
		}
		
		return result;
	}
	
	public Address findById(Integer id) {
		return addressDAO.findById(id);
		//return repo.findById(id);
	}

	@Override
	public Iterable<Address> findAll() {
	//	return repo.findAll();
		return addressDAO.findAll();
	}
	
	@Override
	@Transactional
	public void save(Address address) {

		//repo.save(address);
		addressDAO.save(address);
		
	}
	
	@Transactional
	public Address edit(Address address, Integer stateprovinceid) {
		Address actual = null;
		
		if(address.getAddressid() != null) {
//			Optional<Address> optional = repo.findById(address.getAddressid());
			Address optional = addressDAO.findById(address.getAddressid());
			if(optional!=null) {
				address.setStateprovince(spDAO.findById(stateprovinceid));
//				//save(address);
//				actual = findById(address.getAddressid()).get();
				actual = addressDAO.update(address);
			}
		}
		System.out.println(actual.toString());
		return actual;
		
	}

}
