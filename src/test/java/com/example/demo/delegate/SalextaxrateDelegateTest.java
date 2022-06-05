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
import co.edu.icesi.dev.uccareapp.transport.delegate.SalextaxrateDelegateImp;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;
import co.edu.icesi.dev.uccareapp.transport.services.StateprovinceServiceImp;

@ContextConfiguration(classes = Application.class)
@SpringBootTest
public class SalextaxrateDelegateTest {
	
	@Mock
	RestTemplate rest;
	
	@InjectMocks
	SalextaxrateDelegateImp delegate;
	Salestaxrate str1;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void  setup() {
		MockitoAnnotations.initMocks(this);
		str1 = new Salestaxrate();
		str1.setName("IVA");
		str1.setSalestaxrateid(1);
    	str1.setTaxrate(new BigDecimal(19));
		
	}
	
	public void setup2() {
		Mockito.when(rest.postForEntity("http://localhost:8080/api/tax/", str1,Salestaxrate.class))
		.thenReturn(new ResponseEntity<>(str1,HttpStatus.OK));
		
		delegate.save(str1);
	}
	
	public void setup3() {
		Stateprovince sp1 = new Stateprovince();
    	sp1.setName("Amazonas");
    	sp1.setStateprovincecode("12345");
    	
    	str1.setStateprovince(sp1);
    	
    	setup2();
		
	}
	
	@Test
	public void testSave() {
		Mockito.when(rest.postForEntity("http://localhost:8080/api/tax/", str1,Salestaxrate.class))
		.thenReturn(new ResponseEntity<>(str1,HttpStatus.OK));
		
		delegate.save(str1);
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/tax/1",Salestaxrate.class))
		.thenReturn(new ResponseEntity<Salestaxrate>(str1,HttpStatus.OK).getBody());
		
		
		assertEquals(delegate.findById(1).getName(), "IVA");
	}
	
	@Test
	public void testFindById() {
		
		setup2();

		Mockito.when(rest.getForObject("http://localhost:8080/api/tax/1",Salestaxrate.class))
		.thenReturn(new ResponseEntity<Salestaxrate>(str1,HttpStatus.OK).getBody());
		
		Salestaxrate found = delegate.findById(1);
		assertEquals(found.getName(), "IVA");
	}
	
	@Test
	public void findAllTest() {
		setup2();
		
		Salestaxrate str2 = new Salestaxrate();
		str2.setName("Aranceles");
		
		Salestaxrate str3 = new Salestaxrate();
		str3.setName("ICA");
		
		Salestaxrate[] crs = {str1,str2,str3};
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/tax/",Salestaxrate[].class))
		.thenReturn(new ResponseEntity<Salestaxrate[]>(crs,HttpStatus.OK).getBody());
		
		Iterable<Salestaxrate> strsResult = delegate.findAll();
		assertNotNull(strsResult);
		
		String names ="";
		for (Salestaxrate t: strsResult) {
			names+= t.getName()+" ";
		}	
		
		assertEquals(names,"IVA Aranceles ICA ");

	}
	
	@Test
	public void testEdit() {
		
		setup2();
		str1.setName("Aranceles");
		
		Mockito.when(rest.patchForObject("http://localhost:8080/api/tax/", str1, Salestaxrate.class))
		.thenReturn(new ResponseEntity<>(str1,HttpStatus.OK).getBody());		
				
		delegate.edit(str1);
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/tax/1",Salestaxrate.class))
		.thenReturn(new ResponseEntity<Salestaxrate>(str1,HttpStatus.OK).getBody());
		
		Salestaxrate found = delegate.findById(1);
		assertEquals(found.getName(), "Aranceles");
		assertEquals(found.getTaxrate(), new BigDecimal(19));
	}
	
	@Test
	public void getStateprovincesTest() {
		
		setup3();
		
		Stateprovince[] sps = {str1.getStateprovince()};
			
		Mockito.when(rest.getForObject("http://localhost:8080/api/tax/stateprovinces/",Stateprovince[].class))
		.thenReturn(new ResponseEntity<Stateprovince[]>(sps,HttpStatus.OK).getBody());
		
		Iterable <Stateprovince> results = delegate.getStateprovinces();
		assertNotNull(results);
		String names ="";
		for (Stateprovince t: results) {
			names+= t.getName()+" ";
		}	
		
		assertEquals(names,"Amazonas ");
		
	}
	
}
