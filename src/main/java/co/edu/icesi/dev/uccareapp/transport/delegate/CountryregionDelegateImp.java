package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;

@Component
public class CountryregionDelegateImp implements CountryregionDelegate{

	public final static String PATH = "http://localhost:8080/api/countryregion/";
	
	private RestTemplate rest;
	
	public CountryregionDelegateImp() {
		rest = new RestTemplate();
	}
	

	@Override
	public Iterable<Countryregion> findAll() {
		Countryregion[] crs = rest.getForObject(PATH, Countryregion[].class);
		return Arrays.asList(crs);
	}

	@Override
	public void save(Countryregion cr) {
		rest.postForEntity(PATH, cr, Countryregion.class);
	}

	@Override
	public Countryregion findById(Integer id) {
		return rest.getForObject(PATH+id, Countryregion.class);
	}


	@Override
	public Countryregion edit(Countryregion countryregion) {
		rest.put(PATH, countryregion, Countryregion.class);
		return countryregion;
	}
	
	

}
