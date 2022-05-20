package com.example.demo.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.dao.SalesTaxRateDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.SalesTerritoryDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.StateProvinceDAO;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.repositories.SalesTerritoryRepository;


@ContextConfiguration(classes = Application.class)
@SpringBootTest
public class TestSalesTaxRateDAO {
	
	@Autowired
	private SalesTaxRateDAO strDAO;
	
	@Autowired
	private StateProvinceDAO spDAO;
	
	private Salestaxrate str;
	
	private Stateprovince sp1;
	
	@BeforeEach
	void initDao() {
		str =  new Salestaxrate();
		str.setName("Aranceles");
    	str.setTaxrate(new BigDecimal(15));
	}
	
	private SalesTerritoryRepository stRepo;	
	
	@Autowired
	private SalesTerritoryDAO stDAO;
	
	private Salesterritory st;
	
	@Autowired
	public TestSalesTaxRateDAO(SalesTerritoryRepository stRepo) {
		this.stRepo = stRepo;
	}
	
	void initSalesterritory() {
		st = new Salesterritory();
    	st.setName("Zona del pacifico");
    	
    	stRepo.save(st);
    	stDAO.save(st);
	}
	
	void initStateprovince() {
		sp1 = new Stateprovince();
    	sp1.setName("Valle del Cauca");
    	sp1.setStateprovincecode("12345");
    	
    	initSalesterritory(); 
    	sp1.setTerritoryid(st.getTerritoryid());
    	//System.out.println(sp1.getTerritoryid());
    	
    	spDAO.save(sp1);
    	
    	str.setStateprovince(sp1);
    	
    	strDAO.save(str);
    	
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findSTRByIdTest() {
		strDAO.save(str);
		
		assertEquals(strDAO.findById(str.getSalestaxrateid()),str);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveSTRTest() {
		strDAO.save(str);
		
		assertTrue(strDAO.findById(str.getSalestaxrateid()).equals(str));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void updateSTRTest() {
		strDAO.save(str);
		
		str.setName("IVA");
    	str.setTaxrate(new BigDecimal(19));
    	
    	strDAO.update(str);
    	
    	Salestaxrate edited = strDAO.findById(str.getSalestaxrateid());
    	
    	assertAll(
				() -> assertEquals(edited.getName(),"IVA"),
				() -> assertEquals(edited.getTaxrate(),new BigDecimal(19))
		);
	
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void deleteSTRTest() {
		strDAO.save(str);
		
		strDAO.delete(str);
		
		assertNull(strDAO.findById(str.getSalestaxrateid()));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findAllSTRTest() {
		strDAO.save(str);
		
		assertThat(strDAO.findAll().size(), equalTo(2));

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void getSTRByNameTest() {
		
		str =  new Salestaxrate();
		str.setName("Carne");
    	str.setTaxrate(new BigDecimal(17));
    	
    	strDAO.save(str);
    	
    	str =  new Salestaxrate();
		str.setName("Carne");
    	str.setTaxrate(new BigDecimal(18));
    	
    	strDAO.save(str);
    	
    	List<Salestaxrate> results = strDAO.getSalestaxrateByName2("Carne");
		assertThat(results.size(), equalTo(2));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void getSTRByStateprovinceTest() {
		initStateprovince();
    	
		str =  new Salestaxrate();
		str.setName("IVA");
    	str.setTaxrate(new BigDecimal(13));
    	str.setStateprovince(sp1);
    	strDAO.save(str);
    	
    	List<Salestaxrate> results = strDAO.getSalestaxrateByStateprovince2(sp1.getStateprovinceid());
		assertThat(results.size(), equalTo(2));
	}
	
	@Test
	@Order(1)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void getStateProvinceAndAddressesTest() {
		initStateprovince();
		
		List<Object[]> results = strDAO.getStateProvinceAndAddresses(st);
		//System.out.println(results.size());
		
			//System.out.println("CRIATURITA DE RUBI");
		for (Object[] i : results) {
			assertNotNull(i);
			Stateprovince x = (Stateprovince)i[0];
//			System.out.println(">>>"+name);
//			assertEquals(name,"Valle del Cauca");
			assertEquals(x.getName(),"Valle del Cauca");
			//System.out.println("i en 0 "+ i[0]);
			//assertEquals(i.getTerritoryid(),st.getTerritoryid());
			//assertEquals(sp1.getStateprovinceid(), i.getStateprovinceid());
		}
	}
}
