package co.edu.icesi.dev.uccareapp.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.services.CountryregionServiceImp;


@RestController
public class CountryregionRestController {

	@Autowired
	private CountryregionServiceImp crService;

	@RequestMapping(value = "/api/countryregion/", method = RequestMethod.GET)
	public Iterable<Countryregion> findAll() {
		return crService.findAll();
	}


	@RequestMapping(value = "/api/countryregion/", method = RequestMethod.POST)
	public void saveCountryregion(@RequestBody Countryregion countryregion) {
		try {
			crService.save(countryregion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@RequestMapping(value = "/api/countryregion/", method = RequestMethod.PUT)
	public Countryregion editCountryregion(@RequestBody Countryregion countryregion) {

		try {
			return crService.edit(countryregion);
		} catch (Exception e) {
			e.printStackTrace();
			return countryregion;
		}
	}

	@RequestMapping(value = "/api/countryregion/{id}", method = RequestMethod.GET)
	public Countryregion findById(@PathVariable("id") Integer id) {
		return crService.findById(id);
	}

	@RequestMapping(value = "/api/countryregion/stateprovince/{id}",method=RequestMethod.GET)
	public Countryregion findByStateprovince(@PathVariable("id") Integer stateprovinceid){
		return crService.findByStateprovince(stateprovinceid);

	}

}
