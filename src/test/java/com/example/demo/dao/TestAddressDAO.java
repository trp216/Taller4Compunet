package com.example.demo.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.dao.AddressDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.StateProvinceDAO;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;

@ContextConfiguration(classes = Application.class)
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
@SpringBootTest
public class TestAddressDAO {
	
	@Autowired
	private AddressDAO addressDAO;
	
	@Autowired
	private StateProvinceDAO spDAO;
	
	private Address address;
	
	private Stateprovince sp1;
	
	@BeforeEach
	void initDao() { 
		
		address = new Address();
		address.setAddressline1("Line 1 of address");
		address.setCity("Bucaramanga");
		address.setAddressline2("Line 2 of address");
		address.setPostalcode("A12366");
		address.setSpatiallocation("Comuna 20");
	}
	
	void initStateprovince() {
		sp1 = new Stateprovince();
    	sp1.setName("Valle del Cauca");
    	sp1.setStateprovincecode("12345");
    	
    	spDAO.save(sp1);
    	
    	address.setStateprovince(sp1);
    	
    	addressDAO.save(address);
    	
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findAddressByIdTest() {
		addressDAO.save(address);
		//System.out.println(address.getAddressid()+"");
		
		assertEquals(addressDAO.findById(address.getAddressid()),address);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void saveAddressTest() {
		//assertNotNull(address);
		//try {
			addressDAO.save(address);
			
			assertTrue(addressDAO.findById(address.getAddressid()).equals(address));
		//} catch (Exception e) {
			// TODO: handle exception
		//}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void updateAddressTest() {
		
		addressDAO.save(address);
		address.setAddressline1("Address line 1 modified");
		address.setCity("City modified");
		address.setAddressline2("Line 2 of address modified");
		address.setSpatiallocation("Comuna 20 modified");
		
		addressDAO.update(address);
		
		Address edited = addressDAO.findById(address.getAddressid());
		
		assertAll(
				() -> assertEquals(edited.getAddressline1(),"Address line 1 modified"),
				() -> assertEquals(edited.getAddressline2(),"Line 2 of address modified"),
				() -> assertEquals(edited.getCity(),"City modified"),
				() -> assertEquals(edited.getSpatiallocation(),"Comuna 20 modified")
				);
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void deleteAddressTest() {
		addressDAO.save(address);
		
		Integer id = address.getAddressid();
		
		addressDAO.delete(address);
		
		assertNull(addressDAO.findById(id));
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void findAllAddressesTest() {
		initDao();
		addressDAO.save(address);
		//System.out.println(">>>" + addressDAO.findAll().size()+"");
		//assertEquals(1,addressDAO.findAll().size());
		assertThat(addressDAO.findAll().size(), equalTo(2));
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void getAddressByCityTest() {
		addressDAO.save(address);
		
		address = new Address();
		address.setAddressline1("Line 1 of address2");
		address.setCity("Bucaramanga");
		address.setAddressline2("Line 2 of address2");
		address.setPostalcode("A12766");
		
		addressDAO.save(address);
		
		List<Address> ad = addressDAO.getAddressByCity2("Bucaramanga");
		assertThat(ad.size(), equalTo(2));
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void getAddressByStateprovinceTest() {
		initStateprovince();
    	
		address = new Address();
		address.setAddressline1("Line 1 of address2");
		address.setCity("Cucuta");
		address.setAddressline2("Line 2 of address2");
		address.setPostalcode("A12766");
		address.setStateprovince(sp1);
		
		addressDAO.save(address);
		
		List<Address> ad = addressDAO.getAddressByStateprovince2(sp1.getStateprovinceid());
		assertThat(ad.size(), equalTo(2));
	}

}
