package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;

@Component
public class AddressDelegateImp implements AddressDelegate{

	public final static String PATH = "http://localhost:8080/api/address/";

	private RestTemplate rest;

	public AddressDelegateImp() {
		rest = new RestTemplate();
	}

	@Override
	public Address editAddress(Address address) {
		rest.put(PATH, address, Salestaxrate.class);
		return address;
	}

	@Override
	public Iterable<Address> findAll() {
		Address[] ads = rest.getForObject(PATH, Address[].class);
		return Arrays.asList(ads);
	}

	@Override
	public void save(Address address) {
		rest.postForEntity(PATH, address, Address.class);
	}

	@Override
	public Address findById(Integer id) {
		return rest.getForObject(PATH+id, Address.class);
	}
	
	public Iterable<Stateprovince> getStateprovinces(){
		Stateprovince[] sps = rest.getForObject(PATH + "stateprovinces/", Stateprovince[].class);
		return Arrays.asList(sps);
	}

}
