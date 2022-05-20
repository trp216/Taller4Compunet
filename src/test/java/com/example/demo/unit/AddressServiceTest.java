package com.example.demo.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.exception.ElementNotFoundException;
import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.repositories.AddressRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.StateprovinceRepository;
import co.edu.icesi.dev.uccareapp.transport.services.AddressService;
import co.edu.icesi.dev.uccareapp.transport.services.AddressServiceImp;
import co.edu.icesi.dev.uccareapp.transport.services.StateprovinceServiceImp;

//@SpringBootTest
@ContextConfiguration
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

	@Mock
	private AddressRepository repo;

	@InjectMocks
	private AddressServiceImp aService;

	@Mock
	private StateprovinceRepository spRepo;

	private Stateprovince sp;

	private Address address;


	@Nested
	class Add{
		
		private void setupScenary4() {
			sp = new Stateprovince();
			sp.setStateprovinceid(124);
			sp.setStateprovincecode("SCO13");
			sp.setName("Cundinamarca");
			sp.setIsonlystateprovinceflag("Y/N");

			when(spRepo.findById(124)).thenReturn(Optional.of(sp));

			address = new Address();
		}

		@Test
		void testSaveAddress1() throws FailedValidationsException, ElementNotFoundException {
			setupScenary4();

			address.setAddressid(001);
			address.setAddressline1("line1");
			address.setAddressline2("line2");
			address.setCity("Bogota");
			address.setPostalcode("BO1234");
			address.setRowguid(14);
			address.setSpatiallocation("SL1");

			when(repo.save(address)).thenReturn(address);

			Address saved = aService.saveAddress(124, address);

			assertNotEquals(saved, null);
			//assertNotNull(saved);

			assertAll(
					()-> assertTrue(saved.getAddressid()==001),
					()-> assertTrue(saved.getAddressline1().equals("line1")),
					()-> assertTrue(saved.getAddressline2().equals("line2")),
					()-> assertTrue(saved.getCity().equals("Bogota")),
					()-> assertTrue(saved.getPostalcode().equals("BO1234")),
					()-> assertTrue(saved.getRowguid()==14),
					()-> assertTrue(saved.getSpatiallocation().equals("SL1"))
					);


		}

		@Test
		void testSaveAddress2() {
			//setupScenary4();

			
			assertThrows(ElementNotFoundException.class, () -> {
				address = new Address();
				address.setAddressid(001);
				address.setAddressline1("line1");
				address.setAddressline2("line2");
				address.setCity("Bogota");
				address.setPostalcode("BO1234");
				address.setRowguid(14);
				address.setSpatiallocation("SL1");

				aService.saveAddress(115, address);
			});
		}

		@Test
		void testSaveAddress3() {
			//setupScenary4();

			address = new Address();
			address.setAddressid(002);

			address.setAddressline2("line2");
			address.setCity("Ibague");
			address.setPostalcode("BO1235");
			address.setRowguid(13);
			address.setSpatiallocation("SL2");

			assertThrows(FailedValidationsException.class, () -> {
				aService.saveAddress(124, address);
			});
		}

		@Test
		void testSaveAddress4() {
			//setupScenary4();

			address = new Address();
			address.setAddressid(002);
			address.setAddressline1("line1");
			address.setAddressline2("line2");
			address.setCity("Ja");
			address.setPostalcode("BO1235");
			address.setRowguid(13);
			address.setSpatiallocation("SL2");

			assertThrows(FailedValidationsException.class, () -> {
				aService.saveAddress(124, address);
			});
		}

		@Test
		void testSaveAddress5() {
			//setupScenary4();

			address = new Address();
			address.setAddressid(002);
			address.setAddressline1("line1");
			address.setAddressline2("line2");
			address.setCity("Ibague");
			address.setPostalcode("BO135");
			address.setRowguid(13);
			address.setSpatiallocation("SL2");

			assertThrows(FailedValidationsException.class, () -> {
				aService.saveAddress(124, address);
			});
		}

		@Test
		void testSaveAddress6() {
			//setupScenary4();

			address = new Address();
			address.setAddressid(002);
			address.setAddressline1("line1");
			address.setAddressline2("line2");
			address.setCity("Ibague");
			address.setPostalcode("BO13544");
			address.setRowguid(13);
			address.setSpatiallocation("SL2");

			assertThrows(FailedValidationsException.class, () -> {
				aService.saveAddress(124, address);
			});
		}

	}

	@Nested
	class Edit{

		private void setupScenary2() {
			sp = new Stateprovince();
			
			when(spRepo.findById(123)).thenReturn(Optional.of(sp));
			

			address = new Address();
			address.setAddressid(003);
			address.setAddressline1("line1");
			address.setAddressline2("line2");
			address.setCity("Girardot");
			address.setPostalcode("BO1232");
			address.setRowguid(11);
			address.setSpatiallocation("SL5");
			address.setStateprovince(sp);
			
			when(repo.findById(003)).thenReturn(Optional.of(address));
			repo.save(address);

			address = new Address();
			address.setAddressid(005);
			address.setAddressline1("line1");
			address.setAddressline2("line2");
			address.setCity("Zipaquira");
			address.setPostalcode("BO1237");
			address.setRowguid(15);
			address.setSpatiallocation("SL6");

			when(repo.findById(005)).thenReturn(Optional.of(address));

			repo.save(address);

			
		}
		
		@Test
		void testEditAddress0() throws FailedValidationsException, ElementNotFoundException {
			//setupScenary2();

			address = new Address();
			address.setAddressid(003);
			address.setAddressline1("line1");
			address.setAddressline2("line2");
			address.setCity("Girardot");
			address.setPostalcode("BO1232");
			address.setRowguid(11);
			address.setSpatiallocation("SL5");
			
			sp = new Stateprovince();
			when(spRepo.findById(123)).thenReturn(Optional.of(sp));
			
			address.setStateprovince(sp);
			
			when(repo.findById(003)).thenReturn(Optional.of(address));
			
			repo.save(address);
			
/////////////////////////////////////////////////////
			
			
			address = new Address();
			address.setCity("Soacha");
			address.setAddressid(003);
			address.setAddressline1("linemodified");
			address.setPostalcode("F12346");
			String str="2022-07-03 09:01:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			address.setModifieddate(timestamp);

			when(repo.save(address)).thenReturn(address);
			Address modified = aService.editAddress(123, address);

			assertNotEquals(modified, null);
			//assertNotNull(modified);

			assertAll(
					()-> assertTrue(modified.getAddressline1().equals("linemodified")),
					()-> assertTrue(modified.getCity().equals("Soacha")),
					()-> assertTrue(modified.getPostalcode().equals("F12346")),
					()-> assertEquals(modified.getModifieddate(),timestamp)
					);
		}
		
		@Test
		void testEditAddress1() {
			//setupScenary2();
			assertThrows(FailedValidationsException.class, ()->{
				address = new Address();
				address.setAddressline1("");
				String str="2022-07-03 09:01:15"; 
				address.setAddressid(003);
				Timestamp timestamp= Timestamp.valueOf(str);  
				address.setModifieddate(timestamp);

				when(repo.findById(003)).thenReturn(Optional.of(address));
				
				aService.editAddress(123,  address);

			});
		}

		@Test
		void testEditAddress2() {
			//setupScenary2();
			Address address2 = new Address();
			address2.setAddressid(005);
			address2.setAddressline1("line1");
			address2.setAddressline2("line2");
			address2.setCity("Zipaquira");
			address2.setPostalcode("BO1237");
			address2.setRowguid(15);
			address2.setSpatiallocation("SL6");
			
			address2.setStateprovince(sp);

			when(repo.findById(005)).thenReturn(Optional.of(address2));

			repo.save(address2);
			
			/////////////
			assertThrows(FailedValidationsException.class, ()->{
				address = new Address();
				address.setPostalcode("F123");
				address.setAddressid(005);
				String str="2022-07-03 09:01:15"; 
				Timestamp timestamp= Timestamp.valueOf(str);  
				address.setModifieddate(timestamp);

				aService.editAddress(123, address);

			});
		}

		@Test
		void testEditAddress3() {
			//setupScenary2();
			assertThrows(FailedValidationsException.class, ()->{
				address = new Address();
				address.setPostalcode("F123456");
				address.setAddressid(005);
				String str="2022-07-03 09:01:15"; 
				Timestamp timestamp= Timestamp.valueOf(str);  
				address.setModifieddate(timestamp);
				
				when(repo.findById(005)).thenReturn(Optional.of(address));
				

				aService.editAddress(123, address);

			});
		}

		@Test
		void testEditAddress4() {
			//setupScenary2();
			assertThrows(FailedValidationsException.class, ()->{
				address = new Address();
				address.setCity("N");
				String str="2022-07-03 09:01:15"; 
				address.setAddressid(003);
				Timestamp timestamp= Timestamp.valueOf(str);  
				address.setModifieddate(timestamp);
				
				when(repo.findById(003)).thenReturn(Optional.of(address));
				

				aService.editAddress(123, address);

			});
		}

		
	}
}
