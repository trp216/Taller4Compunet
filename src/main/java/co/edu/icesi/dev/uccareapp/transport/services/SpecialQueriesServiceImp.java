package co.edu.icesi.dev.uccareapp.transport.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.AddressDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.SalesTerritoryDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.StateProvinceDAO;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;

@Service
public class SpecialQueriesServiceImp {
	
	@Autowired
	private AddressDAO adDAO;
	
	@Autowired
	private SalesTerritoryDAO stDAO;
	
	@Autowired
	private StateProvinceDAO spDAO;
	
	
	@Autowired
	public SpecialQueriesServiceImp(StateProvinceDAO spDAO,AddressDAO adDAO, SalesTerritoryDAO stDAO) {
		this.adDAO = adDAO;
		this.stDAO = stDAO;
		this.spDAO = spDAO;
	}

	
	public List<Address> findAddressesWithSalesorderheader() {

		return adDAO.getAddressesWithSalesorderheader();
	}
	
	public List<Stateprovince> findStateProvinceAndAddresses(){
		List<Object[]> results = spDAO.getStateProvinceAndAddresses();

		List<Stateprovince> sps = new ArrayList<Stateprovince>();
		
		for (Object[] i : results) {
			Stateprovince sp = (Stateprovince)i[0];
			
			Long c = (Long)i[1];
			Integer count = Math.toIntExact(c);
			sp.setAdcount(count);
			sps.add(sp);
			spDAO.update(sp);
		}
		return sps;
	}
	
	public Iterable<Salesterritory> findSalesterritory(){
		return stDAO.findAll();
	}


}
