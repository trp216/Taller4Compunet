package com.example.demo.unit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;
import co.edu.icesi.dev.uccareapp.transport.repositories.SalestaxrateRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.StateprovinceRepository;
import co.edu.icesi.dev.uccareapp.transport.services.SalestaxrateServiceImp;

//@SpringBootTest
@ContextConfiguration(classes = Application.class)
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class SalestaxrateServiceTest {

	@Mock
	private SalestaxrateRepository repo;

	@InjectMocks
	private SalestaxrateServiceImp strService;

	@Mock
	private StateprovinceRepository spRepo;

	private Salestaxrate str;
	
	private Stateprovince sp;


	@Nested
	class Add{

		private void setupScenary2() {
			 sp = new Stateprovince();
			sp.setStateprovinceid(123);
			sp.setStateprovincecode("SCO12");
			sp.setName("Arauca");
			sp.setIsonlystateprovinceflag("Y/N");
			
			when(spRepo.findById(123)).thenReturn(Optional.of(sp));

			str = new Salestaxrate();
		}

		@Test 
		public void testSaveSalestaxrate1() throws FailedValidationsException, ElementNotFoundException {
			setupScenary2();

			Salestaxrate s = new Salestaxrate();
			s.setSalestaxrateid(0103);
			s.setName("Impuesto");
			s.setRowguid(3);
			s.setStateprovince(new Stateprovince());
			s.getStateprovince().setStateprovinceid(123);
			s.setTaxrate(new BigDecimal(0.888));
			s.setTaxtype(1);

			when(repo.save(s)).thenReturn(s);

			Salestaxrate saved = strService.saveSalestaxrate(s, 123);

			assertNotNull(saved);

			assertAll(
					() -> assertTrue(saved.getSalestaxrateid()==0103),
					() -> assertEquals(saved.getName(),"Impuesto"),
					() -> assertTrue(saved.getRowguid()==3),
					() -> assertTrue(saved.getStateprovince().getStateprovinceid()==123),
					() -> assertTrue(saved.getTaxrate().compareTo(new BigDecimal(0.888))==0),
					() -> assertTrue(saved.getTaxtype()==1)

					);
		}

		@Test 
		public void testSaveSalestaxrate2() {
			//setupScenary2();

			Salestaxrate s = new Salestaxrate();
			s.setSalestaxrateid(0104);
			s.setName("Impuesto");
			s.setRowguid(3);
			s.setStateprovince(new Stateprovince());
			s.getStateprovince().setStateprovinceid(123);
			s.setTaxrate(new BigDecimal(0.888));
			s.setTaxtype(1);

			assertThrows(ElementNotFoundException.class, () -> {
				strService.saveSalestaxrate(s, 456);
			});

		}

		@Test 
		public void testSaveSalestaxrate3() {
			//setupScenary2();

			Salestaxrate s = new Salestaxrate();
			s.setSalestaxrateid(0104);
			s.setName("IVA");
			s.setRowguid(3);
			s.setStateprovince(new Stateprovince());
			s.getStateprovince().setStateprovinceid(123);
			s.setTaxrate(new BigDecimal(0.888));
			s.setTaxtype(1);

			assertThrows(FailedValidationsException.class, () -> {
				strService.saveSalestaxrate(s, 123);
			});

		}

		@Test 
		public void testSaveSalestaxrate4() {
			//setupScenary2();

			Salestaxrate s = new Salestaxrate();
			s.setSalestaxrateid(0104);
			s.setName("Impuesto");
			s.setRowguid(3);
			s.setStateprovince(new Stateprovince());
			s.getStateprovince().setStateprovinceid(123);
			s.setTaxrate(new BigDecimal(-0.888));
			s.setTaxtype(1);

			assertThrows(FailedValidationsException.class, () -> {
				strService.saveSalestaxrate(s, 123);
			});

		}
	}

	@Nested
	class Edit{

		private void setupScenary4() {
			Stateprovince sp = new Stateprovince();
			sp.setStateprovinceid(124);
			sp.setStateprovincecode("SCO13");
			sp.setName("Cundinamarca");
			sp.setIsonlystateprovinceflag("Y/N");

			when(spRepo.findById(124)).thenReturn(Optional.of(sp));

		}

		private void setupScenary5() {
			setupScenary4();

			str = new Salestaxrate();
			str.setSalestaxrateid(0101);
			str.setName("Impuesto");
			str.setRowguid(2);
			str.setStateprovince(new Stateprovince());
			str.getStateprovince().setStateprovinceid(124);
			str.setTaxrate(new BigDecimal(0.67));
			str.setTaxtype(2);

			when(repo.findById(0101)).thenReturn(Optional.of(str));

			repo.save(str);
			//when(repo.save(s)).thenReturn(s);
		}
		
		@Test
		public void testEditSalestaxrate0() throws FailedValidationsException, ElementNotFoundException {
			setupScenary5();
			
			str = new Salestaxrate();
			str.setSalestaxrateid(0101);
			str.setName("Azucar");
			str.setTaxrate(new BigDecimal(0.566));
			str.setTaxtype(1);
			String x="2022-07-03 09:01:15"; 
			Timestamp timestamp= Timestamp.valueOf(x); 
			str.setModifieddate(timestamp);

			when(repo.save(str)).thenReturn(str);

			Salestaxrate modified = strService.editSalestaxrate(str, 124);

			assertNotEquals(modified, null);
			//assertNotNull(modified);

			assertAll(
					() -> assertEquals(modified.getName(),"Azucar"),
					() -> assertTrue(modified.getTaxrate().compareTo(new BigDecimal(0.566))==0),
					() -> assertTrue(modified.getTaxtype()==1),
					()-> assertEquals(modified.getModifieddate(),timestamp)
					);

		}

		@Test
		public void testEditSalestaxrate1() {

			//setupScenary5();
			assertThrows(FailedValidationsException.class, ()->{
				str = new Salestaxrate();
				str.setName("IVA");
				str.setSalestaxrateid(0101);
				String x="2022-07-03 09:01:15"; 
				Timestamp timestamp= Timestamp.valueOf(x); 
				str.setModifieddate(timestamp);

				when(repo.findById(0101)).thenReturn(Optional.of(str));
				
				strService.editSalestaxrate(str, 124);

			});
		}

		@Test
		public void testEditSalestaxrate2() {
			//setupScenary5();
			assertThrows(FailedValidationsException.class, ()->{
				str = new Salestaxrate();
				str.setTaxrate(new BigDecimal(-0.566));
				String x="2022-07-03 09:01:15";
				str.setSalestaxrateid(0101);
				Timestamp timestamp= Timestamp.valueOf(x); 
				str.setModifieddate(timestamp);
				
				when(repo.findById(0101)).thenReturn(Optional.of(str));
				

				strService.editSalestaxrate(str, 124);

			});
		}

		

	}


}
