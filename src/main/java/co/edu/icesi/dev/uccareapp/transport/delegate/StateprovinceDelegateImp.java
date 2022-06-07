package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;

@Component
public class StateprovinceDelegateImp implements StateprovinceDelegate{

	public final static String PATH = "http://localhost:8080/api/stateprovince/";

	private RestTemplate rest;

	public StateprovinceDelegateImp() {
		rest = new RestTemplate();
	}

	@Override
	public Iterable<Stateprovince> findAll() {
		Stateprovince[] sps = rest.getForObject(PATH, Stateprovince[].class);
		return Arrays.asList(sps);
	}

	@Override
	public Stateprovince findById(Integer id) {
		return rest.getForObject(PATH+id, Stateprovince.class);
	}

	@Override
	public void save(Stateprovince sp) {
		rest.postForEntity(PATH, sp, Stateprovince.class);
	}

	@Override
	public Stateprovince edit(Stateprovince sp) {
		rest.put(PATH, sp, Stateprovince.class);
		return sp;
	}
	
	public Iterable<Countryregion> getCountryregion(){
		Countryregion[] crs = rest.getForObject(PATH + "countryregion/", Countryregion[].class);
		return Arrays.asList(crs);
	}
	
	public Iterable<Salesterritory> getSalesterritory(){
		Salesterritory[] sts = rest.getForObject(PATH + "salesterritory/", Salesterritory[].class);
		return Arrays.asList(sts);
	}
	
	public Iterable<Stateprovince> findByCountryregion(Integer id){
		Stateprovince[] sps = rest.getForObject(PATH+"countryregion/"+id, Stateprovince[].class);
		return Arrays.asList(sps);
	}

}
