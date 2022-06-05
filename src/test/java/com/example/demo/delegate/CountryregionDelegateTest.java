package com.example.demo.delegate;

//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import co.edu.icesi.dev.uccareapp.transport.delegate.CountryregionDelegateImp;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;

@ContextConfiguration(classes = Application.class)
@SpringBootTest
public class CountryregionDelegateTest {

	@Mock
	RestTemplate rest;
	
	@InjectMocks
	CountryregionDelegateImp crDelegate;
	Countryregion cr1;
	
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void  setup() {
		MockitoAnnotations.initMocks(this);
		cr1 = new Countryregion();
		cr1.setName("Bolivia");
		cr1.setCountryregioncode("B23");
		cr1.setCountryregionid(1);
		
	}
	
	public void setup2() {
		Mockito.when(rest.postForEntity("http://localhost:8080/api/countryregion/", cr1,Countryregion.class))
		.thenReturn(new ResponseEntity<>(cr1,HttpStatus.OK));
		
		crDelegate.save(cr1);
	}
	
	@Test
	public void testSave() {
		Mockito.when(rest.postForEntity("http://localhost:8080/api/countryregion/", cr1,Countryregion.class))
		.thenReturn(new ResponseEntity<>(cr1,HttpStatus.OK));
		
		crDelegate.save(cr1);
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/countryregion/1",Countryregion.class))
		.thenReturn(new ResponseEntity<Countryregion>(cr1,HttpStatus.OK).getBody());
		
		
		assertEquals(crDelegate.findById(1).getName(), "Bolivia");
	}
	
	@Test
	public void testFindById() {
		
		setup2();

		Mockito.when(rest.getForObject("http://localhost:8080/api/countryregion/1",Countryregion.class))
		.thenReturn(new ResponseEntity<Countryregion>(cr1,HttpStatus.OK).getBody());
		
		Countryregion found = crDelegate.findById(1);
		assertEquals(found.getCountryregioncode(), "B23");
	}
	
	@Test
	public void testEdit() {
		
		setup2();
		cr1.setName("Francia");
		
		Mockito.when(rest.patchForObject("http://localhost:8080/api/countryregion/", cr1, Countryregion.class))
		.thenReturn(new ResponseEntity<>(cr1,HttpStatus.OK).getBody());		
				
		crDelegate.edit(cr1);
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/countryregion/1",Countryregion.class))
		.thenReturn(new ResponseEntity<Countryregion>(cr1,HttpStatus.OK).getBody());
		
		Countryregion found = crDelegate.findById(1);
		assertEquals(found.getCountryregioncode(), "B23");
		assertEquals(crDelegate.findById(1).getName(), "Francia");
	}
	
	@Test
	public void findAllTest() {
		setup2();
		
		Countryregion cr2 = new Countryregion();
		cr2.setName("China");
		cr2.setCountryregioncode("C45");
		cr2.setCountryregionid(2);
		
		Countryregion cr3 = new Countryregion();
		cr3.setName("Canada");
		cr3.setCountryregioncode("C17");
		cr3.setCountryregionid(3);
		
		Countryregion[] crs = {cr2,cr3};
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/countryregion/",Countryregion[].class))
		.thenReturn(new ResponseEntity<Countryregion[]>(crs,HttpStatus.OK).getBody());
		
		Iterable<Countryregion> crsResult = crDelegate.findAll();
		assertNotNull(crsResult);
		
		String names ="";
		for (Countryregion t: crsResult) {
			names+= t.getName()+" ";
		}	
		
		assertEquals(names,"China Canada ");

	}
	
}
