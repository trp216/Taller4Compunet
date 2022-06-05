package com.example.demo.delegate;

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
import co.edu.icesi.dev.uccareapp.transport.delegate.StateprovinceDelegateImp;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;

@ContextConfiguration(classes = Application.class)
@SpringBootTest
public class StateprovinceDelegateTest {

	@Mock
	RestTemplate rest;

	@InjectMocks
	StateprovinceDelegateImp delegate;
	Stateprovince sp1;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void  setup() {
		MockitoAnnotations.initMocks(this);
		sp1 = new Stateprovince();
		sp1.setName("Tolima");
		sp1.setStateprovincecode("54738");

	}

	public void setup2() {
		Mockito.when(rest.postForEntity("http://localhost:8080/api/stateprovince/", sp1,Stateprovince.class))
		.thenReturn(new ResponseEntity<>(sp1,HttpStatus.OK));

		delegate.save(sp1);
	}

	public void setup3() {
    	Countryregion cr = new Countryregion();
    	cr.setName("Mexico");
    	cr.setCountryregioncode("M21");
		
		sp1.setCountryregion(cr);
		
		setup2();
	}
	
	@Test
	public void testSave() {
		Mockito.when(rest.postForEntity("http://localhost:8080/api/stateprovince/", sp1,Stateprovince.class))
		.thenReturn(new ResponseEntity<>(sp1,HttpStatus.OK));
		
		delegate.save(sp1);
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/stateprovince/1",Stateprovince.class))
		.thenReturn(new ResponseEntity<Stateprovince>(sp1,HttpStatus.OK).getBody());
		
		
		assertEquals(delegate.findById(1).getName(), "Tolima");
	}
	
	@Test
	public void testFindById() {
		
		setup2();

		Mockito.when(rest.getForObject("http://localhost:8080/api/stateprovince/1",Stateprovince.class))
		.thenReturn(new ResponseEntity<Stateprovince>(sp1,HttpStatus.OK).getBody());
		
		Stateprovince found = delegate.findById(1);
		assertEquals(found.getName(), "Tolima");
		assertEquals(found.getStateprovincecode(),"54738");
	}
	
	@Test
	public void findAllTest() {
		setup2();
		
		Stateprovince sp2 = new Stateprovince();
		sp2.setName("Bolivar");;
		
		Stateprovince[] ads = {sp1,sp2};
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/stateprovince/",Stateprovince[].class))
		.thenReturn(new ResponseEntity<Stateprovince[]>(ads,HttpStatus.OK).getBody());
		
		Iterable<Stateprovince> result = delegate.findAll();
		assertNotNull(result);
		
		String names ="";
		for (Stateprovince t: result) {
			names+= t.getName()+" ";
		}	
		
		assertEquals(names,"Tolima Bolivar ");
		
	}
	
	@Test
	public void testEdit() {
		
		setup2();
		sp1.setStateprovincecode("12345");
		
		Mockito.when(rest.patchForObject("http://localhost:8080/api/stateprovince/", sp1, Stateprovince.class))
		.thenReturn(new ResponseEntity<>(sp1,HttpStatus.OK).getBody());		
				
		delegate.edit(sp1);
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/stateprovince/1",Stateprovince.class))
		.thenReturn(new ResponseEntity<Stateprovince>(sp1,HttpStatus.OK).getBody());
		
		Stateprovince found = delegate.findById(1);
		assertEquals(found.getName(), "Tolima");
		assertEquals(found.getStateprovincecode(), "12345");
	}
	
	@Test
	public void getCountryregionsTest() {
		
		setup3();
		
		Countryregion[] sps = {sp1.getCountryregion()};
			
		Mockito.when(rest.getForObject("http://localhost:8080/api/stateprovince/countryregion/",Countryregion[].class))
		.thenReturn(new ResponseEntity<Countryregion[]>(sps,HttpStatus.OK).getBody());
		
		Iterable <Countryregion> results = delegate.getCountryregion();
		assertNotNull(results);
		String names ="";
		for (Countryregion t: results) {
			names+= t.getName()+" ";
		}	
		
		assertEquals(names,"Mexico ");
		
	}
	
	@Test
	public void getSalesterritoryTest() {
		
		Salesterritory territory = new Salesterritory();
		territory.setName("Buenaventura");
		
		sp1.setTerritoryid(territory.getTerritoryid());
		
		setup2();
		
		assertEquals(territory.getTerritoryid(),sp1.getTerritoryid());
		
		Salesterritory[] sps = {territory};
			
		Mockito.when(rest.getForObject("http://localhost:8080/api/stateprovince/salesterritory/",Salesterritory[].class))
		.thenReturn(new ResponseEntity<Salesterritory[]>(sps,HttpStatus.OK).getBody());
		
		Iterable <Salesterritory> results = delegate.getSalesterritory();
		assertNotNull(results);
		String names ="";
		for (Salesterritory t: results) {
			names+= t.getName()+" ";
		}	
		
		assertEquals(names,"Buenaventura ");
		
	}
}
