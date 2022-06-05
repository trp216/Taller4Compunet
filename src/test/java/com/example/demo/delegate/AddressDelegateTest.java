package com.example.demo.delegate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.delegate.AddressDelegateImp;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;

@ContextConfiguration(classes = Application.class)
@SpringBootTest
public class AddressDelegateTest {
	
	@Mock
	RestTemplate rest;
	
	@InjectMocks
	AddressDelegateImp delegate;
	Address a1;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void  setup() {
		MockitoAnnotations.initMocks(this);
		a1 = new Address();
    	a1.setAddressline1("Line 1 of address");
    	a1.setCity("Medellin");
    	a1.setAddressline2("Line 2 of address");
    	a1.setPostalcode("A67890");
    	a1.setSpatiallocation("Comuna 20");
		
	}
	
	public void setup2() {
		Mockito.when(rest.postForEntity("http://localhost:8080/api/address/", a1,Address.class))
		.thenReturn(new ResponseEntity<>(a1,HttpStatus.OK));
		
		delegate.save(a1);
	}
	
	public void setup3() {
		Stateprovince sp1 = new Stateprovince();
    	sp1.setName("Vaupes");
    	sp1.setStateprovincecode("12345");
    	
    	a1.setStateprovince(sp1);
    	
    	setup2();
		
	}
	
	@Test
	public void testSave() {
		Mockito.when(rest.postForEntity("http://localhost:8080/api/address/", a1,Address.class))
		.thenReturn(new ResponseEntity<>(a1,HttpStatus.OK));
		
		delegate.save(a1);
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/address/1",Address.class))
		.thenReturn(new ResponseEntity<Address>(a1,HttpStatus.OK).getBody());
		
		
		assertEquals(delegate.findById(1).getCity(), "Medellin");
	}
	
	@Test
	public void testFindById() {
		
		setup2();

		Mockito.when(rest.getForObject("http://localhost:8080/api/address/1",Address.class))
		.thenReturn(new ResponseEntity<Address>(a1,HttpStatus.OK).getBody());
		
		Address found = delegate.findById(1);
		assertEquals(found.getCity(), "Medellin");
		assertEquals(found.getSpatiallocation(),"Comuna 20");
	}
	
	@Test
	public void findAllTest() {
		setup2();
		
		Address a2 = new Address();
		a2.setCity("Manizales");
		
		Address[] ads = {a1,a2};
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/address/",Address[].class))
		.thenReturn(new ResponseEntity<Address[]>(ads,HttpStatus.OK).getBody());
		
		Iterable<Address> result = delegate.findAll();
		assertNotNull(result);
		
		String cities ="";
		for (Address t: result) {
			cities+= t.getCity()+" ";
		}	
		
		assertEquals(cities,"Medellin Manizales ");
		
	}
	
	@Test
	public void testEdit() {
		
		setup2();
		a1.setAddressline1("Line 1 modified");
		
		Mockito.when(rest.patchForObject("http://localhost:8080/api/address/", a1, Address.class))
		.thenReturn(new ResponseEntity<>(a1,HttpStatus.OK).getBody());		
				
		delegate.editAddress(a1);
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/address/1",Address.class))
		.thenReturn(new ResponseEntity<Address>(a1,HttpStatus.OK).getBody());
		
		Address found = delegate.findById(1);
		assertEquals(found.getCity(), "Medellin");
		assertEquals(found.getAddressline1(), "Line 1 modified");
	}
	
	@Test
	public void getStateprovincesTest() {
		
		setup3();
		
		Stateprovince[] sps = {a1.getStateprovince()};
			
		Mockito.when(rest.getForObject("http://localhost:8080/api/address/stateprovinces/",Stateprovince[].class))
		.thenReturn(new ResponseEntity<Stateprovince[]>(sps,HttpStatus.OK).getBody());
		
		Iterable <Stateprovince> results = delegate.getStateprovinces();
		assertNotNull(results);
		String names ="";
		for (Stateprovince t: results) {
			names+= t.getName()+" ";
		}	
		
		assertEquals(names,"Vaupes ");
		
	}

}
