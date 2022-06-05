package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;

@Component
public class SpecialQueriesDelegateImp {

	public final static String PATH = "http://localhost:8080/api/specialqueries/";

	private RestTemplate rest;

	public SpecialQueriesDelegateImp() {
		rest = new RestTemplate();
	}
	
	public Iterable<Address> findAddressesWithSalesorderheader() {
		Address[] sps = rest.getForObject(PATH+"ad/", Address[].class);
		return Arrays.asList(sps);
	}
	
}
