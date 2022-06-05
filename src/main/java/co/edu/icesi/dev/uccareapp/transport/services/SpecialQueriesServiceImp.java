package co.edu.icesi.dev.uccareapp.transport.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.AddressDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.CountryRegionDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.SalesTerritoryDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.StateProvinceDAO;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;

@Service
public class SpecialQueriesServiceImp {
	
	@Autowired
	private AddressDAO adDAO;
	
	@Autowired
	public SpecialQueriesServiceImp(AddressDAO adDAO) {
		this.adDAO = adDAO;
	}
	
	public List<Address> findAddressesWithSalesorderheader() {
		ArrayList<Address> ads = new ArrayList<Address>();
		if(adDAO.getAddressesWithSalesorderheader()!=null) {
			
			ads = (ArrayList<Address>) adDAO.getAddressesWithSalesorderheader();	
		}
		System.out.println(ads.toString());
		return ads;
	}

}
