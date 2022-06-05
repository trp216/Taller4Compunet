package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;

@Component
public class SalextaxrateDelegateImp implements SalestaxrateDelegate{

	public final static String PATH = "http://localhost:8080/api/tax/";
	
	private RestTemplate rest;
	
	public SalextaxrateDelegateImp() {
		rest = new RestTemplate();
	}
	
	@Override
	public Salestaxrate edit(Salestaxrate str) {
		rest.put(PATH, str, Salestaxrate.class);
		return str;
	}

	@Override
	public Salestaxrate findById(Integer id) {
		return rest.getForObject(PATH+id, Salestaxrate.class);
	}

	@Override
	public Iterable<Salestaxrate> findAll() {
		Salestaxrate[] strs = rest.getForObject(PATH, Salestaxrate[].class);
		return Arrays.asList(strs);
	}

	@Override
	public void save(Salestaxrate str) {
		rest.postForEntity(PATH, str, Salestaxrate.class);
	}
	
	public Iterable<Stateprovince> getStateprovinces(){
		Stateprovince[] sps = rest.getForObject(PATH + "stateprovinces/", Stateprovince[].class);
		return Arrays.asList(sps);
	}

}
