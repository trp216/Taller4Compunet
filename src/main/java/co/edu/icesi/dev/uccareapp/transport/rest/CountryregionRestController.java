package co.edu.icesi.dev.uccareapp.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.services.CountryregionServiceImp;


@RestController
public class CountryregionRestController {
	
	@Autowired
	private CountryregionServiceImp crService;

	@RequestMapping(value = "/countryregion/api/", method = RequestMethod.GET)
	public Iterable<Countryregion> findAll() {
		System.out.println( crService.findAll().toString());
		return crService.findAll();
	}
	
	
	@RequestMapping(value = "/api/countryregion/add/", method = RequestMethod.POST)
	public void saveCountryregion(@RequestBody Countryregion countryregion) {
		try {
			crService.save(countryregion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/api/countryregion/edit/{id}", method = RequestMethod.PUT)
	public Countryregion editCountryregion(@RequestBody Countryregion countryregion) {
		
		try {
			return crService.edit(countryregion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return countryregion;
		}
	}

}
