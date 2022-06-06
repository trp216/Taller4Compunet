package co.edu.icesi.dev.uccareapp.transport.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.services.SpecialQueriesServiceImp;

@RestController
public class SpecialQueriesRestController {

	@Autowired
	private SpecialQueriesServiceImp sqService;

	@RequestMapping(value = "/api/specialqueries/ad", method = RequestMethod.GET)
	public Iterable<Address> findAddressesWithSalesorderheader() {

		return sqService.findAddressesWithSalesorderheader();
	}

	@RequestMapping(value = "/api/specialqueries/sp", method = RequestMethod.POST)
	public List<Stateprovince> findStateProvinceAndAddresses(@RequestBody  Salesterritory st) {
		return sqService.findStateProvinceAndAddresses(st);
	}

	@RequestMapping(value = "/api/specialqueries/salesterritory", method = RequestMethod.GET)
	public Iterable<Salesterritory> getSalesterritory() {

		return sqService.findSalesterritory();
	}

}
