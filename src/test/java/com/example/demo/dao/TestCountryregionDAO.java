package com.example.demo.dao;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.dao.CountryRegionDAO;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;

@ContextConfiguration(classes = Application.class)
@SpringBootTest
public class TestCountryregionDAO {
	
	@Autowired
	private CountryRegionDAO crDAO;
	
	private Countryregion cr;
	
	@BeforeEach
	void initDao() { 
		cr = new Countryregion();
		cr.setName("Colombia");
    	cr.setCountryregioncode("C12");
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findCRByIdTest() {
		crDAO.save(cr);
		assertEquals(crDAO.findById(cr.getCountryregionid()),cr);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveCRTest() {
		crDAO.save(cr);
		assertTrue(crDAO.findById(cr.getCountryregionid()).equals(cr));
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void updateCRTest() {
		crDAO.save(cr);
		
		cr.setName("Venezuela");
    	cr.setCountryregioncode("C13");
    	
    	crDAO.update(cr);
    	
    	Countryregion edited = crDAO.findById(cr.getCountryregionid());
    	
    	assertAll(
    			() -> assertEquals(edited.getName(),"Venezuela"),
    			() -> assertEquals(edited.getCountryregioncode(),"C13")
    	);
    	
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void deleteCRTest() {
		crDAO.save(cr);
		
		crDAO.delete(cr);
		
		assertNull(crDAO.findById(cr.getCountryregionid()));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findAllCRTest() {
		crDAO.save(cr);
		
		assertThat(crDAO.findAll().size(), equalTo(2));
	}
	
	

}
