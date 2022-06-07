package co.edu.icesi.dev.uccareapp.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.services.CountryregionServiceImp;
import co.edu.icesi.dev.uccareapp.transport.services.SalesTerritoryService;
import co.edu.icesi.dev.uccareapp.transport.services.StateprovinceServiceImp;

@RestController
public class StateprovinceRestController {
	
	@Autowired
	private StateprovinceServiceImp spService;
	
	@Autowired
	private CountryregionServiceImp crService;
	
	@Autowired
	private SalesTerritoryService stService;
	
	@RequestMapping(value = "/api/stateprovince/", method = RequestMethod.GET)
	public Iterable<Stateprovince> findAll() {
		return spService.findAll();
	}
	
	@RequestMapping(value = "/api/stateprovince/", method = RequestMethod.POST)
	public void saveSP(@RequestBody Stateprovince stateprovince) {
		try {
			spService.save(stateprovince);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/api/stateprovince/", method = RequestMethod.PUT)
	public Stateprovince editSP(@RequestBody Stateprovince stateprovince) {
		
		try {
				return spService.edit(stateprovince);
			
		} catch (Exception e) {
			e.printStackTrace();
			return stateprovince;
		}
	}
	
	@RequestMapping(value = "/api/stateprovince/{id}", method = RequestMethod.GET)
	public Stateprovince findById(@PathVariable("id") Integer id) {
		return spService.findById(id);
	}
	
	@RequestMapping(value = "/api/stateprovince/countryregion", method = RequestMethod.GET)
	public Iterable<Countryregion> getCountryregion() {
		
		return crService.findAll();
	}

	@RequestMapping(value = "/api/stateprovince/salesterritory", method = RequestMethod.GET)
	public Iterable<Salesterritory> getSalesterritory() {
		
		return stService.findAll();
	}

	@RequestMapping(value = "/api/stateprovince/countryregion/{id}", method = RequestMethod.GET)
	public Iterable<Stateprovince> findByCountryregion(@PathVariable("id") Integer crid){
		return spService.findByCountryregion(crid);
	}
	
}
