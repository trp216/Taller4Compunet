package com.example.demo.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.dao.CountryRegionDAO;
import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.services.CountryregionServiceImp;

//@SpringBootTest
@ContextConfiguration(classes = Application.class)
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class CountryregionServiceTest {

	@Mock
	CountryRegionDAO crRepo;

	@InjectMocks
	CountryregionServiceImp crService;

	private Countryregion countryregion;


	@Nested
	class Add{

		@Test
		void testSaveCountryregion1() {
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("12345");
			assertThrows(FailedValidationsException.class, () -> {
				crService.saveCountryRegion(cr);
			});
		}

		@Test
		void testSaveCountryregion2() throws FailedValidationsException {
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("123");
			cr.setName(".....");

			when(crRepo.save(cr)).thenReturn(cr);

			Countryregion saved = crService.saveCountryRegion(cr);
			

			assertNotNull(saved);
			assertTrue(saved.getCountryregioncode().equals("123"));

		}

		@Test
		void testSaveCountryregion3() {
			Countryregion cr = new Countryregion();
			cr.setName("nads");
			cr.setCountryregioncode("...");
			assertThrows(FailedValidationsException.class, () -> {
				crService.saveCountryRegion(cr);
			});
		}

		@Test
		void testSaveCountryregion4() throws FailedValidationsException {
			Countryregion cr = new Countryregion();
			cr.setName("Taiwan");
			cr.setCountryregioncode("...");
			
			when(crService.saveCountryRegion(cr)).thenReturn(cr);

			Countryregion saved = crService.saveCountryRegion(cr);
			

			assertNotNull(saved);
			assertTrue(saved.getName().equals("Taiwan"));

		}

		@Test
		void testSaveCountryregion5() throws FailedValidationsException {
			Countryregion cr = new Countryregion();
			cr.setName("Francia");
			cr.setCountryregioncode("87");
			
			when(crService.saveCountryRegion(cr)).thenReturn(cr);
			

			Countryregion saved = crService.saveCountryRegion(cr);
			
			assertNotNull(saved);
			assertTrue(saved.getName().equals("Francia"));
			assertTrue(saved.getCountryregioncode().equals("87"));

		}

		@Test
		void testSaveCountryregion6() throws FailedValidationsException {



			assertThrows(FailedValidationsException.class, () -> {
				Countryregion cr = new Countryregion();
				cr.setName("Tir");
				cr.setCountryregioncode("006523");

				when(crService.saveCountryRegion(cr)).thenReturn(cr);
				crService.saveCountryRegion(cr);
			});

		}

	}

	@Nested
	class Edit{

		private void setupScenary1(){
			countryregion = new Countryregion();
			countryregion.setCountryregionid(7);
			countryregion.setCountryregioncode("C11");
			countryregion.setName("Colombia");
		}

		@Test
		void testEditCountryregion1() throws FailedValidationsException {
			setupScenary1();
			countryregion.setCountryregioncode("C02");
			countryregion.setCountryregionid(7);
			String str="2022-06-04 09:01:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			countryregion.setModifieddate(timestamp);
			
			when(crRepo.update(countryregion)).thenReturn(countryregion);

			Countryregion modified = crService.editCountryRegion(countryregion);


			assertNotNull(modified);
			assertTrue(modified.getCountryregioncode().equals("C02"));
			assertTrue(modified.getName().equals("Colombia"));
		}

		@Test
		void testEditCountryregion2() throws FailedValidationsException {
			setupScenary1();
			countryregion.setName("Mexico");
			countryregion.setCountryregionid(7);
			String str="2022-06-04 09:01:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			countryregion.setModifieddate(timestamp);

			//when(crRepo.findById(2)).thenReturn(Optional.of(countryregion));
			when(crRepo.update(countryregion)).thenReturn(countryregion);
			
			Countryregion modified = crService.editCountryRegion( countryregion);


			assertNotNull(modified);
			assertTrue(modified.getCountryregioncode().equals("C11"));
			assertTrue(modified.getName().equals("Mexico"));
		}

		@Test
		void testEditCountryregion3() throws FailedValidationsException {
			setupScenary1();
			countryregion.setName("Ecuador");
			countryregion.setCountryregionid(7);
			String str="2022-06-04 09:01:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			countryregion.setModifieddate(timestamp);
			countryregion.setCountryregioncode("E2");
			
			//when(crRepo.findById(3)).thenReturn(Optional.of(countryregion));
			when(crRepo.update(countryregion)).thenReturn(countryregion);
			

			Countryregion modified = crService.editCountryRegion( countryregion);


			assertNotNull(modified);
			assertTrue(modified.getCountryregioncode().equals("E2"));
			assertTrue(modified.getName().equals("Ecuador"));
		}

	}

}
