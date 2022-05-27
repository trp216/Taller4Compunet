package co.edu.icesi.dev.uccareapp.transport.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.CountryRegionDAO;
import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;

@Service
public class CountryregionServiceImp implements CountryregionService{

	//private CountryregionRepository repo;
	private CountryRegionDAO crDAO;

//	@Autowired
//	public CountryregionServiceImp(CountryregionRepository repo) {
//		this.repo = repo;
//	}
	
	@Autowired
	public CountryregionServiceImp(CountryRegionDAO crDAO) {
		super();
		this.crDAO = crDAO;
	}

	@Override
	@Transactional
	public Countryregion saveCountryRegion(Countryregion cr) throws FailedValidationsException {
		Countryregion result = null;

		if(cr.getCountryregioncode()==null || cr.getCountryregioncode().length()<1 || cr.getCountryregioncode().length()>4) {
			throw new FailedValidationsException("El código debe tener entre 1 y 4 caracteres");
		}		
		else if(cr.getName()==null || cr.getName().length()<5) {
			throw new FailedValidationsException("El nombre debe tener al menos cinco caracteres");
		}
		else {
			//result = this.repo.save(cr);
			result = crDAO.save(cr);
		}

		return result;

	}

	

	@Override
	@Transactional
	public Countryregion editCountryRegion(Countryregion cr) throws FailedValidationsException {
		Countryregion result = null;

		if(cr.getCountryregionid()!=null) {
			//Optional<Countryregion> old = repo.findById(cr.getCountryregionid());
//			if(old.isPresent()) {
//				result = saveCountryRegion(cr);
//
//			}
			 result = crDAO.update(cr);
		}

		return result;
	}

	@Override
	public Countryregion findById(Integer id) {
		return crDAO.findById(id);
	}

	@Override
	public Iterable<Countryregion> findAll() {
		return crDAO.findAll();
	}

	@Transactional
	public void save(Countryregion cr) {
		//	
		crDAO.save(cr);
		//}
	}

	@Transactional
	public Countryregion edit(Countryregion countryregion) {
		Countryregion actual = null;

		if(countryregion.getCountryregionid() != null) {
//			Optional<Countryregion> optional = repo.findById(countryregion.getCountryregionid());
//			if(optional.isPresent()) {
//				save(countryregion);
//				actual = findById(countryregion.getCountryregionid()).get();
//			}
			actual = crDAO.update(countryregion);
		}

		return actual;
	}
}
