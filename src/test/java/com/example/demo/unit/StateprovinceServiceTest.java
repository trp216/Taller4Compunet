package com.example.demo.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.repositories.CountryregionRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.SalesTerritoryRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.StateprovinceRepository;
import co.edu.icesi.dev.uccareapp.transport.services.StateprovinceService;
import co.edu.icesi.dev.uccareapp.transport.services.StateprovinceServiceImp;

//@SpringBootTest
//@ContextConfiguration(classes = Application.class)
@ContextConfiguration
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class StateprovinceServiceTest {

	@Mock
	private StateprovinceRepository spRepo;

	@Mock
	private CountryregionRepository crRepo;

	@Mock
	private SalesTerritoryRepository stRepo;

	@InjectMocks
	private StateprovinceServiceImp spService;

	private Stateprovince sp;
	
	private Countryregion cr;
	
	public void setupScenary3() {
	//	sp = new Stateprovince();
		
		Salesterritory st = new Salesterritory();
		st.setTerritoryid(3456);
		st.setCostlastyear(new BigDecimal(20057500.76));
		st.setCostytd(new BigDecimal(5070043.221));
		st.setCountryregioncode("C11");
		st.setSalesGroup("Carvajal");
		st.setName("Zona Franca del Pacifico");
		st.setRowguid(2);
		st.setSaleslastyear(new BigDecimal(6000700));
		st.setSalesytd(new BigDecimal(427300));

		//Salesterritory candy = new Salesterritory();
		
		when(stRepo.findById(3456)).thenReturn(Optional.of(st));

//		when(stRepo.save(st)).thenReturn(st);
//		
//		stRepo.save(st);
		
		
	}

	@Nested
	class Add{

		public void setupScenary1() {
			cr = new Countryregion();
			cr.setCountryregioncode("C11");
			cr.setCountryregionid(7);
			cr.setName("Colombia");

			Countryregion kk = new Countryregion();
			
			when(crRepo.findById(7)).thenReturn(Optional.of(kk));
			
		}

		@Test
		public void testSaveStateprovince1() throws FailedValidationsException, ElementNotFoundException {

			sp = new Stateprovince();
			
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("C11");
			cr.setCountryregionid(7);
			cr.setName("Colombia");

			when(crRepo.findById(7)).thenReturn(Optional.of(cr));
			
			
			Salesterritory st = new Salesterritory();
			st.setTerritoryid(3456);
			st.setCostlastyear(new BigDecimal(20057500.76));
			st.setCostytd(new BigDecimal(5070043.221));
			st.setCountryregioncode("C11");
			st.setSalesGroup("Carvajal");
			st.setName("Zona Franca del Pacifico");
			st.setRowguid(2);
			st.setSaleslastyear(new BigDecimal(6000700));
			st.setSalesytd(new BigDecimal(427300));

			Salesterritory candy = new Salesterritory();
			
			when(stRepo.findById(3456)).thenReturn(Optional.of(st));

			
			sp.setStateprovinceid(57450);
			sp.setStateprovincecode("SCO18");
			sp.setName("Bolivar");
			sp.setIsonlystateprovinceflag("Y/N");
			sp.setTerritoryid(3456);
			sp.setCountryregion(cr);
			
			when(spRepo.save(sp)).thenReturn(sp);

			Stateprovince saved = spService.saveStateprovince(sp,3456,7);

			//assertNotNull(saved);
			
			assertNotEquals(saved,null);

			assertAll(
					() -> assertTrue(saved.getStateprovinceid()==57450),
					() -> assertEquals(saved.getName(),"Bolivar"),
					() -> assertEquals(saved.getStateprovincecode(),"SCO18"),
					() -> assertEquals(saved.getIsonlystateprovinceflag(),"Y/N")

					);
		}

		@Test
		public void testSaveStateprovince2() {
			

			assertThrows(ElementNotFoundException.class, () -> {
				
//				setupScenary1();
//				setupScenary3();
				
				sp = new Stateprovince();
				sp.setStateprovinceid(57450);
				sp.setStateprovincecode("SCO19");
				sp.setName("Valle del Cauca");
				sp.setIsonlystateprovinceflag("Y/N");
				sp.setTerritoryid(3470);
				spService.saveStateprovince(sp, 3470,7);
			});
		}

		@Test
		public void testSaveStateprovince3() {
//			setupScenary1();
//			setupScenary3();
//			
			sp = new Stateprovince();
			sp.setStateprovinceid(57630);
			sp.setStateprovincecode("SCO19");
			sp.setName("Valle del Cauca");
			sp.setIsonlystateprovinceflag("Y/N");


			Salesterritory aux = new Salesterritory();
			aux.setTerritoryid(5555);
			aux.setCountryregioncode("C12");
			when(stRepo.findById(5555)).thenReturn(Optional.of(aux));
			
			sp.setTerritoryid(5555);

			assertThrows(ElementNotFoundException.class, () -> {
				spService.saveStateprovince(sp, 5555,6);
			});
		}

		@Test
		public void testSaveStateprovince4() {
			

			assertThrows(FailedValidationsException.class, () -> {
//				setupScenary1();
//				setupScenary3();
				
				sp = new Stateprovince();
				sp.setStateprovinceid(57);
				sp.setStateprovincecode("SC19");
				sp.setName("Valle del Cauca");
				sp.setIsonlystateprovinceflag("Y/N");
				sp.setTerritoryid(3456);
				
				spService.saveStateprovince(sp, 3456,7);
			});
		}

		@Test
		public void testSaveStateprovince5() {
			
			assertThrows(FailedValidationsException.class, () -> {
//				setupScenary1();
//				setupScenary3();
				
				sp = new Stateprovince();
				sp.setStateprovinceid(579990);
				sp.setStateprovincecode("SCPPO19");
				sp.setName("Valle del Cauca");
				sp.setIsonlystateprovinceflag("Y/N");
				sp.setTerritoryid(3456);

				spService.saveStateprovince(sp, 3456,7);
			});
		}

		@Test
		public void testSaveStateprovince6() {
//			setupScenary1();
//			setupScenary3();
			
			sp = new Stateprovince();
			sp.setStateprovinceid(57630);
			sp.setStateprovincecode("SCO19");
			sp.setName("Pen");
			sp.setIsonlystateprovinceflag("Y/N");
			sp.setTerritoryid(3456);

			assertThrows(FailedValidationsException.class, () -> {
				spService.saveStateprovince(sp, 3456,7);
			});
		}

		@Test
		public void testSaveStateprovince7() {
//			setupScenary1();
//			setupScenary3();
		
			sp = new Stateprovince();
			sp.setStateprovinceid(57630);
			sp.setStateprovincecode("SCO19");
			sp.setName("Valle del Cauca");
			sp.setIsonlystateprovinceflag("Sea");
			sp.setTerritoryid(3456);

			assertThrows(FailedValidationsException.class, () -> {
				spService.saveStateprovince(sp, 3456,7);
			});
		}

	}

	@Nested
	class Edit{

		public void setupScenary2() {
			
			//setupScenary3();
			
			sp = new Stateprovince();
			sp.setStateprovinceid(123);
			sp.setStateprovincecode("SCO12");
			sp.setName("Arauca");
			sp.setIsonlystateprovinceflag("Y/N");
			
			Salesterritory st = new Salesterritory();
			st.setTerritoryid(3456);
			st.setCostlastyear(new BigDecimal(20057500.76));
			st.setCostytd(new BigDecimal(5070043.221));
			st.setCountryregioncode("C11");
			st.setSalesGroup("Carvajal");
			st.setName("Zona Franca del Pacifico");
			st.setRowguid(2);
			st.setSaleslastyear(new BigDecimal(6000700));
			st.setSalesytd(new BigDecimal(427300));

			when(stRepo.findById(3456)).thenReturn(Optional.of(st));

			
			sp.setTerritoryid(3456);
			
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("C11");
			cr.setCountryregionid(8);
			cr.setName("Colombia");
			sp.setCountryregion(cr);
//
			when(crRepo.findById(7)).thenReturn(Optional.of(cr));
//			
			when(spRepo.findById(123)).thenReturn(Optional.of(sp));
			spRepo.save(sp);
			
		}
		
		@Test
		public void testEditStateprovince1() throws FailedValidationsException, ElementNotFoundException {
			setupScenary2() ;
			
			sp.setStateprovincecode("SCO15");
			sp.setStateprovinceid(123);
			sp.setName("Amazonas");
			sp.setIsonlystateprovinceflag("Y/N");
			String str="2022-04-08 09:01:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			sp.setModifieddate(timestamp);
			
			when(spRepo.save(sp)).thenReturn(sp);

			Stateprovince modified = spService.editStateprovince(sp, 3456,7);

			assertNotEquals(modified, null);
			
			assertAll(
					()-> assertEquals(modified.getStateprovincecode(),"SCO15"),
					()-> assertTrue(modified.getName().equals("Amazonas")),
					()-> assertTrue(modified.getIsonlystateprovinceflag().equals("Y/N")),
					()-> assertEquals(modified.getModifieddate(),timestamp)
			);
			
		}
		
		@Test
		public void testEditStateprovince2() {
			//setupScenary2() ;
			
			assertThrows(FailedValidationsException.class, ()->{
			
				Stateprovince sp1 = new Stateprovince();				
				sp1.setStateprovincecode("S15");
				sp1.setStateprovinceid(123);
				String str="2022-04-08 09:01:15"; 
				Timestamp timestamp= Timestamp.valueOf(str);  
				sp1.setModifieddate(timestamp);

				when(spRepo.findById(123)).thenReturn(Optional.of(sp1));
				
				spService.editStateprovince(sp1, 3456,7);

			});
		}
		
		@Test
		public void testEditStateprovince3() {
			//setupScenary2() ;
			
			assertThrows(FailedValidationsException.class, ()->{
				Stateprovince sp1 = new Stateprovince();
				sp1.setStateprovincecode("SCO125");
				sp1.setStateprovinceid(123);
				String str="2022-04-08 09:01:15"; 
				Timestamp timestamp= Timestamp.valueOf(str);  
				sp1.setModifieddate(timestamp);
				
				when(spRepo.findById(123)).thenReturn(Optional.of(sp1));
				

				spService.editStateprovince(sp1, 3456,7);

			});
		}
		
		@Test
		public void testEditStateprovince4() {
			//setupScenary2() ;
			
			assertThrows(FailedValidationsException.class, ()->{
				Stateprovince sp1 = new Stateprovince();
				sp1.setName("Ojo");
				sp1.setStateprovinceid(123);
				String str="2022-04-08 09:01:15"; 
				Timestamp timestamp= Timestamp.valueOf(str);  
				sp1.setModifieddate(timestamp);
				
				when(spRepo.findById(123)).thenReturn(Optional.of(sp1));
				

				spService.editStateprovince(sp1, 3456,7);

			});
		}

	}

}
