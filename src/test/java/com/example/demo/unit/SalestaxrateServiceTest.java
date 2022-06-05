package com.example.demo.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.dao.SalesTaxRateDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.StateProvinceDAO;
import co.edu.icesi.dev.uccareapp.transport.exception.ElementNotFoundException;
import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;
import co.edu.icesi.dev.uccareapp.transport.services.SalestaxrateServiceImp;

//@SpringBootTest
@ContextConfiguration(classes = Application.class)
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class SalestaxrateServiceTest {

	@Mock
	private SalesTaxRateDAO repo;

	@InjectMocks
	private SalestaxrateServiceImp strService;

	@Mock
	private StateProvinceDAO spRepo;

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
			
			when(spRepo.findById(123)).thenReturn(sp);

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
		@Test
		public void testEditSalestaxrate0() throws FailedValidationsException, ElementNotFoundException {
			str = new Salestaxrate();
			str.setSalestaxrateid(0101);
			str.setName("Azucar");
			str.setTaxrate(new BigDecimal(0.566));
			str.setTaxtype(1);
			String x="2022-07-03 09:01:15"; 
			Timestamp timestamp= Timestamp.valueOf(x); 
			str.setModifieddate(timestamp);

			when(repo.update(str)).thenReturn(str);

			Salestaxrate modified = strService.editSalestaxrate(str, 124);

			assertNotEquals(modified, null);

			assertAll(
					() -> assertEquals(modified.getName(),"Azucar"),
					() -> assertTrue(modified.getTaxrate().compareTo(new BigDecimal(0.566))==0),
					() -> assertTrue(modified.getTaxtype()==1),
					()-> assertEquals(modified.getModifieddate(),timestamp)
					);

		}


	}


}
