package co.edu.icesi.dev.uccareapp.transport.services;

import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.SalesTaxRateDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.StateProvinceDAO;
import co.edu.icesi.dev.uccareapp.transport.exception.ElementNotFoundException;
import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;
import co.edu.icesi.dev.uccareapp.transport.repositories.SalestaxrateRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.StateprovinceRepository;

@Service
public class SalestaxrateServiceImp implements SalestaxrateService{

//	private SalestaxrateRepository repo;
	@Autowired
	private SalesTaxRateDAO strDAO;

	//private StateprovinceRepository spRepo;
	@Autowired
	private StateProvinceDAO spDAO;

//	@Autowired
//	public SalestaxrateServiceImp(SalestaxrateRepository repo, StateprovinceRepository spRepo) {
//		super();
//		this.repo = repo;
//		this.spRepo = spRepo;
//	}
	
	@Autowired 
	public SalestaxrateServiceImp(SalesTaxRateDAO strDAO, StateProvinceDAO spDAO) {
		super();
		this.strDAO = strDAO;
		this.spDAO = spDAO;
	}

	@Override
	public Salestaxrate saveSalestaxrate(Salestaxrate str, int stateprovinceid) throws FailedValidationsException, ElementNotFoundException {
		Salestaxrate result  = null;
		//		
		//		if(str.getName()==null || str.getName().isBlank() || str.getName().length()<5) {
		//			throw new FailedValidationsException("El nombre debe tener al menos 5 caracteres");
		//		}
		//		else if(str.getTaxrate().signum()<0) {
		//			throw new FailedValidationsException("La tasa no debe ser negativa");
		//			
		//		}
		//		else {
		//			Optional<Stateprovince> opt1 = this.spRepo.findById(stateprovinceid);
		//			if(opt1.isPresent()) {
		//				str.setStateprovinceid(stateprovinceid);
		//				result = this.repo.save(str);
		//			}else {
		//				throw new ElementNotFoundException("El estado provincia no existe");
		//			}
		//		}
		//		
		return result;
	}

	

	@Override
	@Transactional
	public Salestaxrate editSalestaxrate(Salestaxrate str, int stateprovinceid) throws FailedValidationsException, ElementNotFoundException{
		Salestaxrate result  = null;
		
		
//		if(str.getSalestaxrateid()!=null) {
//			Optional<Salestaxrate> old = repo.findById(str.getSalestaxrateid());
//			if(old.isPresent()) {
//				result = saveSalestaxrate(str, stateprovinceid);
//			}
//		}
		return result;
	}

	public Salestaxrate findById(Integer id) {
		//return repo.findById(id);
		return strDAO.findById(id);
	}

	public Iterable<Salestaxrate> findAll() {
		//return repo.findAll();
		return strDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Salestaxrate str) {

		//repo.save(str);
		strDAO.save(str);

	}

	@Transactional
	public Salestaxrate edit(Salestaxrate tax, Integer stateprovinceid) {

		Salestaxrate actual = null;

//		if(tax.getSalestaxrateid() != null) {
//			Optional<Salestaxrate> optional = repo.findById(tax.getSalestaxrateid());
//			if(optional.isPresent()) {
//				tax.setStateprovince(spRepo.findById(stateprovinceid).get());
//				save(tax);
//				actual = findById(tax.getSalestaxrateid()).get();
//			}
//		}

		tax.setStateprovince(spDAO.findById(stateprovinceid));
		actual = strDAO.update(tax);

		return actual;

	}

}
