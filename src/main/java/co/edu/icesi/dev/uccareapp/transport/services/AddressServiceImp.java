package co.edu.icesi.dev.uccareapp.transport.services;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.AddressDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.StateProvinceDAO;
import co.edu.icesi.dev.uccareapp.transport.exception.ElementNotFoundException;
import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Person;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;

@Service
public class AddressServiceImp implements AddressService{


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
			Stateprovince opt1 = spDAO.findById(stateProvinceId);
			if(opt1!=null) {
				address.setStateprovince(opt1);
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
			Address old = addressDAO.findById(address.getAddressid());
			if(old!=null) {
				result = addressDAO.update(address);
			}

		}

		return result;
	}

	@Override
	public Address findById(Integer id) {
		return addressDAO.findById(id);
	}

	@Override
	public Iterable<Address> findAll() {
		return addressDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Address address) {

		addressDAO.save(address);

	}

	@Transactional
	public Address edit(Address address, Integer stateprovinceid) {
		Address actual = null;

		if(address.getAddressid() != null) {
			Address optional = addressDAO.findById(address.getAddressid());
			if(optional!=null) {
				address.setStateprovince(spDAO.findById(stateprovinceid));
				actual = addressDAO.update(address);
			}
		}
		//System.out.println(actual.toString());
		return actual;

	}
	
	 public Address findByStateprovince(Integer id) {
	        return addressDAO.findByStateprovince(id);
	    }

}
