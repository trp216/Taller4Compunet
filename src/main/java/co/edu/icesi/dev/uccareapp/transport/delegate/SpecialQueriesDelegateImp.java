package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;

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
	
	public List<Stateprovince> findStateProvinceAndAddresses(Salesterritory st) {
		
		Stateprovince[] sp = rest.getForObject(PATH+"sp/", Stateprovince[].class);
		return Arrays.asList(sp);
		
	}
	
	public Iterable<Salesterritory> getSalesterritory(){
		Salesterritory[] sts = rest.getForObject(PATH + "salesterritory/", Salesterritory[].class);
		return Arrays.asList(sts);
	}
}
