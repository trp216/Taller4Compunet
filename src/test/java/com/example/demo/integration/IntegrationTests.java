package com.example.demo.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.dao.AddressDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.CountryRegionDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.SalesTaxRateDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.SalesTerritoryDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.StateProvinceDAO;
import co.edu.icesi.dev.uccareapp.transport.exception.ElementNotFoundException;
import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.services.AddressServiceImp;
import co.edu.icesi.dev.uccareapp.transport.services.CountryregionServiceImp;
import co.edu.icesi.dev.uccareapp.transport.services.SalesTerritoryService;
import co.edu.icesi.dev.uccareapp.transport.services.SalestaxrateServiceImp;
import co.edu.icesi.dev.uccareapp.transport.services.StateprovinceServiceImp;

@ContextConfiguration(classes = Application.class)
@ExtendWith(SpringExtension.class)
public class IntegrationTests {

	private CountryRegionDAO crRepo;
	private CountryregionServiceImp crService;

	private SalesTerritoryDAO stRepo;
	//private SalesTerritoryService stService;

	private StateProvinceDAO spRepo;
	private StateprovinceServiceImp spService;

	private AddressDAO aRepo;
	private AddressServiceImp aService;

	private SalesTaxRateDAO stxRepo;
	private SalestaxrateServiceImp stxService;


	private Countryregion country;
	private Salesterritory st;

	@Autowired
	public IntegrationTests(CountryRegionDAO crRepo, CountryregionServiceImp crService,
	SalesTerritoryDAO stRepo, StateProvinceDAO spRepo, StateprovinceServiceImp spService,
	AddressDAO aRepo, AddressServiceImp aService, SalesTaxRateDAO stxRepo,
			SalestaxrateServiceImp stxService, SalesTerritoryService stService) {
		super();
		this.crRepo = crRepo;
		this.crService = crService;
		this.stRepo = stRepo;
		this.spRepo = spRepo;
		this.spService = spService;
		this.aRepo = aRepo;
		this.aService = aService;
		this.stxRepo = stxRepo;
		this.stxService = stxService;
		//this.stService = stService;
	}

	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	class CountryregionTests{

		@Test
		@Order(1)
		void itSaveCountry1() throws FailedValidationsException {
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("2240");
			cr.setName("Canada");

			Countryregion saved = crService.saveCountryRegion(cr);
			System.out.println("1");

			assertNotEquals(saved,null);
			assertTrue(cr.getCountryregioncode().equals( "2240"));
			assertTrue(cr.getName().equals("Canada"));
		}

		@Test
		@Order(2)
		void itSaveCountry2() throws FailedValidationsException {
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("78090");
			cr.setCountryregionid(11);
			cr.setName("Irlanda");

			assertThrows(FailedValidationsException.class, () ->{
				crService.saveCountryRegion(cr);
				System.out.println("2");
			});
		}

		@Test
		@Order(3)
		void itSaveCountry3() throws FailedValidationsException {
			Countryregion cr = new Countryregion();
			cr.setCountryregioncode("");
			cr.setCountryregionid(11);
			cr.setName("Irlanda");

			assertThrows(FailedValidationsException.class, () ->{
				crService.saveCountryRegion(cr);
				System.out.println("3");
			});
		}

		@Test
		@Order(4)
		void itSaveCountry4() throws FailedValidationsException {
			Countryregion cr = new Countryregion();
			cr.setCountryregionid(11);
			cr.setCountryregioncode("7809");
			cr.setName("Cus");

			assertThrows(FailedValidationsException.class, () ->{
				crService.saveCountryRegion(cr);
				System.out.println("4");
			});
		}

		@Test
		@Order(5)
		void itEditCountry2() throws FailedValidationsException {

			Countryregion cr = crRepo.findById(1);


			cr.setCountryregioncode("6942");
			cr.setName("Alemania");
			String str="2022-03-04 16:07:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			cr.setModifieddate(timestamp);

			Countryregion saved = crService.editCountryRegion(cr);

			assertNotEquals(saved,null);
			assertTrue(cr.getCountryregioncode().equals("6942"));
			assertTrue(cr.getName().equals("Alemania"));

		}
	}

	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	class SalesTaxRateTests{

		@Test
		@Order(1)
		void itSaveSales1() throws FailedValidationsException, ElementNotFoundException {
			Stateprovince sp1 = spRepo.save(new Stateprovince());
			

			Salestaxrate s = new Salestaxrate();
			s.setName("Impuesto");
			s.setRowguid(3);
			s.setStateprovince(sp1);
			s.setTaxrate(new BigDecimal(0.888));
			s.setTaxtype(1);

			Salestaxrate saved = stxService.save(s);

			assertNotEquals(saved,null);

			assertAll(
					() -> assertTrue(saved.getName().equals("Impuesto")),
					() -> assertTrue(saved.getRowguid()==3),
					() -> assertTrue(saved.getStateprovince().getStateprovinceid()==sp1.getStateprovinceid()),
					() -> assertTrue(saved.getTaxrate().compareTo(new BigDecimal(0.888))==0),
					() -> assertTrue(saved.getTaxtype()==1)

					);

		}


		@Test
		@Order(4)
		void itEditSales1() throws FailedValidationsException, ElementNotFoundException {
			Stateprovince sp1 = spRepo.save(new Stateprovince());
			Salestaxrate s = new Salestaxrate();
			s.setName("Impuesto");
			s.setRowguid(3);
			s.setStateprovince(sp1);
			s.setTaxrate(new BigDecimal(0.888));
			s.setTaxtype(1);

			Salestaxrate saved = stxRepo.save(s);

			Salestaxrate old = stxRepo.findById(saved.getSalestaxrateid());

			old.setName("Azucar");
			old.setTaxrate(new BigDecimal(0.566));
			old.setTaxtype(2);
			String str="2022-03-04 17:37:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			old.setModifieddate(timestamp);

			Salestaxrate edited = stxService.editSalestaxrate(old, sp1.getStateprovinceid());

			assertNotEquals(edited,null);

			assertAll(
					() -> assertTrue(edited.getName().equals("Azucar")),
					() -> assertTrue(edited.getRowguid()==3),
					() -> assertTrue(edited.getStateprovince().getStateprovinceid()==sp1.getStateprovinceid()),
					() -> assertTrue(edited.getTaxrate().compareTo(new BigDecimal(0.566))==0),
					() -> assertTrue(edited.getTaxtype()==2)

					);
		}

	}

	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	class AddressTests{

		@Test
		@Order(1)
		void itSaveAddress1() throws FailedValidationsException, ElementNotFoundException {
			Stateprovince sp1 = spRepo.save(new Stateprovince());

			Address a = new Address();
			a.setAddressline1("line1");
			a.setAddressline2("line2");
			a.setCity("Cali");
			a.setPostalcode("BO1237");
			a.setRowguid(15);
			a.setSpatiallocation("SL1");

			Address saved = aService.saveAddress(sp1.getStateprovinceid(), a);

			assertNotEquals(saved,null);

			assertAll(
					()-> assertTrue(saved.getAddressline1().equals("line1")),
					()-> assertTrue(saved.getAddressline2().equals("line2")),
					()-> assertTrue(saved.getCity().equals("Cali")),
					()-> assertTrue(saved.getPostalcode().equals("BO1237")),
					()-> assertTrue(saved.getRowguid()==15),
					()-> assertTrue(saved.getSpatiallocation().equals("SL1"))
					);

		}

		@Test
		@Order(2)
		void itSaveAddress2() throws FailedValidationsException, ElementNotFoundException {
			Address a = new Address();
			a.setAddressline1("line1");
			a.setAddressline2("line2");
			a.setCity("Pasto");
			a.setPostalcode("BO1236");
			a.setRowguid(17);
			a.setSpatiallocation("SL1");

			assertThrows(ElementNotFoundException.class, () ->{
				aService.saveAddress(2, a);
			});

		}

		@Test
		@Order(3)
		void itSaveAddress3() throws FailedValidationsException, ElementNotFoundException {
			Address a = new Address();
			a.setAddressline1("");
			a.setAddressline2("line2");
			a.setCity("Pasto");
			a.setPostalcode("BO1236");
			a.setRowguid(17);
			a.setSpatiallocation("SL1");

			assertThrows(FailedValidationsException.class, () ->{
				aService.saveAddress(1, a);
			});


		}
		
		@Test
		@Order(4)
		void itSaveAddress4() throws FailedValidationsException, ElementNotFoundException {
			Address a = new Address();
			a.setAddressline1("line1");
			a.setAddressline2("line2");
			a.setCity("Po");
			a.setPostalcode("BO1236");
			a.setRowguid(17);
			a.setSpatiallocation("SL1");

			assertThrows(FailedValidationsException.class, () ->{
				aService.saveAddress(1, a);
			});


		}
		
		@Test
		@Order(5)
		void itSaveAddress5() throws FailedValidationsException, ElementNotFoundException {
			Address a = new Address();
			a.setAddressline1("line1");
			a.setAddressline2("line2");
			a.setCity("Pasto");
			a.setPostalcode("BO36");
			a.setRowguid(17);
			a.setSpatiallocation("SL1");

			assertThrows(FailedValidationsException.class, () ->{
				aService.saveAddress(1, a);
			});


		}
		
		@Test
		@Order(6)
		void itSaveAddress6() throws FailedValidationsException, ElementNotFoundException {
			Address a = new Address();
			a.setAddressline1("line1");
			a.setAddressline2("line2");
			a.setCity("Pasto");
			a.setPostalcode("BO1234436");
			a.setRowguid(17);
			a.setSpatiallocation("SL1");

			assertThrows(FailedValidationsException.class, () ->{
				aService.saveAddress(1, a);
			});


		}
		
		@Test
		@Order(7)
		void itEditAddress1() throws FailedValidationsException, ElementNotFoundException {
			Stateprovince sp1 = spRepo.save(new Stateprovince());

			Address a = new Address();
			a.setAddressline1("line1");
			a.setAddressline2("line2");
			a.setCity("Cali");
			a.setPostalcode("BO1237");
			a.setRowguid(15);
			a.setSpatiallocation("SL1");

			Address saved = aService.saveAddress(sp1.getStateprovinceid(), a);


			Address old = aRepo.findById(saved.getAddressid());
			
			old.setAddressline1("linemodified");
			old.setCity("Barranquilla");
			old.setPostalcode("BA1244");
			String str="2022-03-04 18:17:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			old.setModifieddate(timestamp);
			
			Address modified = aService.editAddress(sp1.getStateprovinceid(), old);

			assertNotEquals(modified,null);

			assertAll(
					()-> assertTrue(modified.getAddressline1().equals("linemodified")),
					()-> assertTrue(modified.getCity().equals("Barranquilla")),
					()-> assertTrue(modified.getPostalcode().equals("BA1244"))
					);	
		}
	}

		@Nested
		@TestMethodOrder(OrderAnnotation.class)
		class StateprovinceTest{
			
			void setup() {
				
				st = new Salesterritory();
				stRepo.save(st);
				
				country = new Countryregion();
				crRepo.save(country);
				
			}
			
			@Test
			@Order(1)
			void itSaveState1() throws FailedValidationsException, ElementNotFoundException {
				setup();
				
				Stateprovince sp = new Stateprovince();
				sp.setStateprovincecode("54785");
				sp.setName("Valle del Cauca");
				sp.setIsonlystateprovinceflag("Y/N");
				sp.setTerritoryid(st.getTerritoryid());
				sp.setCountryregion(country);
				
				Stateprovince saved = spService.saveStateprovince(sp, st.getTerritoryid(), country.getCountryregionid());
				
				assertNotEquals(saved,null);
	
				assertAll(
						() -> assertTrue(saved.getName().equals("Valle del Cauca")),
						() -> assertTrue(saved.getStateprovincecode().equals("54785")),
						() -> assertTrue(saved.getIsonlystateprovinceflag().equals("Y/N"))
	
						);
			}
			
			@Test
			@Order(2)
			void itSaveState2() throws FailedValidationsException, ElementNotFoundException {

				setup();
				
				Stateprovince sp = new Stateprovince();
				sp.setStateprovinceid(2);
				sp.setStateprovincecode("SCO20");
				sp.setName("Vichada");
				sp.setIsonlystateprovinceflag("Y/N");
				sp.setTerritoryid(3);
				sp.setCountryregion(country);
				
				

				assertThrows(ElementNotFoundException.class, () ->{
					spService.saveStateprovince(sp, 3, country.getCountryregionid());
				});
			}
			
			@Test
			@Order(3)
			void itSaveState3() throws FailedValidationsException, ElementNotFoundException {
				setup();
				
				Stateprovince sp = new Stateprovince();
				sp.setStateprovinceid(2);
				sp.setStateprovincecode("SCO20");
				sp.setName("Vichada");
				sp.setIsonlystateprovinceflag("Y/N");
				sp.setTerritoryid(st.getTerritoryid());
				sp.setCountryregion(country);
				
				

				assertThrows(ElementNotFoundException.class, () ->{
					spService.saveStateprovince(sp, st.getTerritoryid(), 8);
				});
			}
			
			@Test
			@Order(4)
			void itSaveState4() throws FailedValidationsException, ElementNotFoundException {
				setup();
				
				Stateprovince sp = new Stateprovince();
				sp.setStateprovinceid(2);
				sp.setStateprovincecode("20");
				sp.setName("Vichada");
				sp.setIsonlystateprovinceflag("Y/N");
				sp.setTerritoryid(st.getTerritoryid());
				sp.setCountryregion(country);
				
				

				assertThrows(FailedValidationsException.class, () ->{
					spService.saveStateprovince(sp, st.getTerritoryid(), country.getCountryregionid());
				});
			}
			
			@Test
			@Order(5)
			void itSaveState5() throws FailedValidationsException, ElementNotFoundException {
				setup();
				
				Stateprovince sp = new Stateprovince();
				sp.setStateprovinceid(2);
				sp.setStateprovincecode("238978430");
				sp.setName("Vichada");
				sp.setIsonlystateprovinceflag("Y/N");
				sp.setTerritoryid(st.getTerritoryid());
				sp.setCountryregion(country);
				
				

				assertThrows(FailedValidationsException.class, () ->{
					spService.saveStateprovince(sp, st.getTerritoryid(), country.getCountryregionid());
				});
			}
			
			@Test
			@Order(6)
			void itSaveState6() throws FailedValidationsException, ElementNotFoundException {
				setup();
				
				Stateprovince sp = new Stateprovince();
				sp.setStateprovinceid(2);
				sp.setStateprovincecode("SCO20");
				sp.setName("da");
				sp.setIsonlystateprovinceflag("Y/N");
				sp.setTerritoryid(st.getTerritoryid());
				sp.setCountryregion(country);
				
				

				assertThrows(FailedValidationsException.class, () ->{
					spService.saveStateprovince(sp, st.getTerritoryid(), country.getCountryregionid());
				});
			}
			
			@Test
			@Order(7)
			void itSaveState7() throws FailedValidationsException, ElementNotFoundException {
				setup();
				
				Stateprovince sp = new Stateprovince();
				sp.setStateprovinceid(2);
				sp.setStateprovincecode("SCO20");
				sp.setName("Vichada");
				sp.setIsonlystateprovinceflag("X");
				sp.setTerritoryid(st.getTerritoryid());
				sp.setCountryregion(country);
				
				

				assertThrows(FailedValidationsException.class, () ->{
					spService.saveStateprovince(sp, st.getTerritoryid(), country.getCountryregionid());
				});
			}
			
			@Test
			@Order(8)
			void itEditState1() throws FailedValidationsException, ElementNotFoundException {
				setup();
				Stateprovince sp = new Stateprovince();
				sp.setStateprovincecode("54785");
				sp.setName("Valle del Cauca");
				sp.setIsonlystateprovinceflag("Y/N");
				sp.setTerritoryid(st.getTerritoryid());
				sp.setCountryregion(country);
				
				Stateprovince saved = spService.saveStateprovince(sp, st.getTerritoryid(), country.getCountryregionid());
				
				Stateprovince old = spRepo.findById(saved.getStateprovinceid());
				old.setStateprovincecode("98745");
				old.setName("Guaviare");
				old.setIsonlystateprovinceflag("Y/N");
				String str="2022-04-08 18:43:15"; 
				Timestamp timestamp= Timestamp.valueOf(str);  
				old.setModifieddate(timestamp);
				
				Stateprovince modified = spService.edit(old);
				System.out.println("Returned object with id "+modified.getStateprovinceid());
				assertNotEquals(modified,null);

				assertAll(
						()-> assertTrue(modified.getStateprovincecode().equals("98745")),
						()-> assertTrue(modified.getName().equals("Guaviare")),
						()-> assertTrue(modified.getIsonlystateprovinceflag().equals("Y/N"))
						);
			}
			
		}

}
