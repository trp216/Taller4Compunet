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

@ContextConfiguration(classes = Application.class)
@ExtendWith(MockitoExtension.class)
public class SalestaxrateServiceTest {

	@Mock
	private SalesTaxRateDAO repo;

	@InjectMocks
	private SalestaxrateServiceImp strService;

	@Mock
	private StateProvinceDAO spRepo;

	private Salestaxrate str;


	@Nested
	class Add {

		@Test
		public void testSaveSalestaxrate1() throws FailedValidationsException, ElementNotFoundException {

			Salestaxrate s = new Salestaxrate();
			s.setSalestaxrateid(0103);
			s.setName("Impuesto");
			s.setRowguid(3);
			s.setStateprovince(new Stateprovince());
			s.getStateprovince().setStateprovinceid(123);
			s.setTaxrate(new BigDecimal(0.888));
			s.setTaxtype(1);

			when(repo.save(s)).thenReturn(s);

			Salestaxrate saved = strService.save(s);

			assertNotNull(saved);

			assertAll(
					() -> assertTrue(saved.getSalestaxrateid() == 0103),
					() -> assertEquals(saved.getName(), "Impuesto"),
					() -> assertTrue(saved.getRowguid() == 3),
					() -> assertTrue(saved.getStateprovince().getStateprovinceid() == 123),
					() -> assertTrue(saved.getTaxrate().compareTo(new BigDecimal(0.888)) == 0),
					() -> assertTrue(saved.getTaxtype() == 1)

			);
		}

	}

	@Nested
	class Edit {
		@Test
		public void testEditSalestaxrate0() throws FailedValidationsException, ElementNotFoundException {
			str = new Salestaxrate();
			str.setSalestaxrateid(0101);
			str.setName("Azucar");
			str.setTaxrate(new BigDecimal(0.566));
			str.setTaxtype(1);
			String x = "2022-07-03 09:01:15";
			Timestamp timestamp = Timestamp.valueOf(x);
			str.setModifieddate(timestamp);

			when(repo.update(str)).thenReturn(str);

			Salestaxrate modified = strService.editSalestaxrate(str, 124);

			assertNotEquals(modified, null);

			assertAll(
					() -> assertEquals(modified.getName(), "Azucar"),
					() -> assertTrue(modified.getTaxrate().compareTo(new BigDecimal(0.566)) == 0),
					() -> assertTrue(modified.getTaxtype() == 1),
					() -> assertEquals(modified.getModifieddate(), timestamp));

		}

	}

}
