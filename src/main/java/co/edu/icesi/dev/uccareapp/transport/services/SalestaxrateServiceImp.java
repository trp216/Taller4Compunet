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
public class SalestaxrateServiceImp implements SalestaxrateService {

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
	public Salestaxrate save(Salestaxrate str) {
		return strDAO.save(str);
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
		return result;
	}

	@Override
	@Transactional
	public Salestaxrate editSalestaxrate(Salestaxrate str, int stateprovinceid)
			throws FailedValidationsException, ElementNotFoundException {
		Salestaxrate result = strDAO.update(str);
		return result;
	}

}
