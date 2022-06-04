package co.edu.icesi.dev.uccareapp.transport.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.SalesTaxRateDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.StateProvinceDAO;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;
import co.edu.icesi.dev.uccareapp.transport.exception.ElementNotFoundException;
import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;


@Service
public class SalestaxrateServiceImp implements SalestaxrateService{

	@Autowired
	private SalesTaxRateDAO strDAO;

	@Autowired
	private StateProvinceDAO spDAO;


	
	@Autowired 
	public SalestaxrateServiceImp(SalesTaxRateDAO strDAO, StateProvinceDAO spDAO) {
		super();
		this.strDAO = strDAO;
		this.spDAO = spDAO;
	}

	@Override
	public Salestaxrate findById(Integer id) {
		
		return strDAO.findById(id);
	}

	@Override
	public Iterable<Salestaxrate> findAll() {
		
		return strDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Salestaxrate str) {

		strDAO.save(str);

	}

	@Override
	@Transactional
	public Salestaxrate edit(Salestaxrate tax, Integer stateprovinceid) {

		Salestaxrate actual = null;



		tax.setStateprovince(spDAO.findById(stateprovinceid));
		actual = strDAO.update(tax);

		return actual;

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

}
