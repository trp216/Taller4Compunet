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
import co.edu.icesi.dev.uccareapp.transport.exception.ElementNotFoundException;
import co.edu.icesi.dev.uccareapp.transport.exception.FailedValidationsException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.repositories.AddressRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.CountryregionRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.SalesTerritoryRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.SalestaxrateRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.StateprovinceRepository;
import co.edu.icesi.dev.uccareapp.transport.services.AddressServiceImp;
import co.edu.icesi.dev.uccareapp.transport.services.CountryregionServiceImp;
import co.edu.icesi.dev.uccareapp.transport.services.SalesTerritoryService;
import co.edu.icesi.dev.uccareapp.transport.services.SalestaxrateServiceImp;
import co.edu.icesi.dev.uccareapp.transport.services.StateprovinceServiceImp;

@ContextConfiguration(classes = Application.class)
@ExtendWith(SpringExtension.class)
public class IntegrationTests {

	private CountryregionRepository crRepo;
	private CountryregionServiceImp crService;

	private SalesTerritoryRepository stRepo;
	//private SalesTerritoryService stService;

	private StateprovinceRepository spRepo;
	private StateprovinceServiceImp spService;

	private AddressRepository aRepo;
	private AddressServiceImp aService;

	private SalestaxrateRepository stxRepo;
	private SalestaxrateServiceImp stxService;


	private Countryregion country;
	private Salesterritory st;

	@Autowired
	public IntegrationTests(CountryregionRepository crRepo, CountryregionServiceImp crService,
			SalesTerritoryRepository stRepo, StateprovinceRepository spRepo, StateprovinceServiceImp spService,
			AddressRepository aRepo, AddressServiceImp aService, SalestaxrateRepository stxRepo,
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
			cr.setCountryregionid(1);
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
		@Order(6)
		void itEditCountry1() throws FailedValidationsException {
			Countryregion cr = crRepo.findById(1).get();
			cr.setCountryregioncode("22400");
			String str="2022-03-04 16:07:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			cr.setModifieddate(timestamp);

			assertThrows(FailedValidationsException.class, () ->{
				crService.editCountryRegion(cr);
				System.out.println("5");
			});
		}

		@Test
		@Order(5)
		void itEditCountry2() throws FailedValidationsException {

			Countryregion cr = crRepo.findById(1).get();


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

		@Test
		@Order(7)
		void itEditCountry3() throws FailedValidationsException {
			Countryregion cr = crRepo.findById(1).get();
			cr.setName("Cuba");
			String str="2022-03-04 16:07:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			cr.setModifieddate(timestamp);

			assertThrows(FailedValidationsException.class, () ->{
				crService.editCountryRegion(cr);
			});
		}
	}

	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	class SalesTaxRateTests{

		@Test
		@Order(1)
		void itSaveSales1() throws FailedValidationsException, ElementNotFoundException {
			Stateprovince sp1 = new Stateprovince();
			sp1.setStateprovinceid(1);
			spRepo.save(sp1);

			Salestaxrate s = new Salestaxrate();
			s.setName("Impuesto");
			s.setSalestaxrateid(1);
			s.setRowguid(3);
			s.setStateprovince(new Stateprovince());
			s.getStateprovince().setStateprovinceid(1);
			s.setTaxrate(new BigDecimal(0.888));
			s.setTaxtype(1);

			Salestaxrate saved = stxService.saveSalestaxrate(s, 1);

			assertNotEquals(saved,null);

			assertAll(
					() -> assertTrue(saved.getName().equals("Impuesto")),
					() -> assertTrue(saved.getRowguid()==3),
					() -> assertTrue(saved.getStateprovince().getStateprovinceid()==1),
					() -> assertTrue(saved.getTaxrate().compareTo(new BigDecimal(0.888))==0),
					() -> assertTrue(saved.getTaxtype()==1)

					);

		}

		@Test
		@Order(2)
		void itSaveSales2() throws FailedValidationsException, ElementNotFoundException {
			Salestaxrate s = new Salestaxrate();
			s.setName("Beb");
			s.setRowguid(3);
			s.setStateprovince(new Stateprovince());
			s.getStateprovince().setStateprovinceid(1);
			s.setTaxrate(new BigDecimal(0.78));
			s.setTaxtype(1);

			assertThrows(FailedValidationsException.class, () ->{
				stxService.saveSalestaxrate(s, 1);
			});
		}

		@Test
		@Order(3)
		void itSaveSales3() throws FailedValidationsException, ElementNotFoundException {
			Salestaxrate s = new Salestaxrate();
			s.setName("Bebida Alcoholica");
			s.setRowguid(3);
			s.setStateprovince(new Stateprovince());
			s.getStateprovince().setStateprovinceid(1);
			s.setTaxrate(new BigDecimal(-0.78));
			s.setTaxtype(1);

			assertThrows(FailedValidationsException.class, () ->{
				stxService.saveSalestaxrate(s, 1);
			});
		}

		@Test
		@Order(3)
		void itSaveSales4() throws FailedValidationsException, ElementNotFoundException {
			Salestaxrate s = new Salestaxrate();
			s.setName("Bebida Alcoholica");
			s.setRowguid(3);
			s.setStateprovince(new Stateprovince());
			s.getStateprovince().setStateprovinceid(2);
			s.setTaxrate(new BigDecimal(0.78));
			s.setTaxtype(1);

			assertThrows(ElementNotFoundException.class, () ->{
				stxService.saveSalestaxrate(s, 2);
			});
		}

		@Test
		@Order(4)
		void itEditSales1() throws FailedValidationsException, ElementNotFoundException {
			Salestaxrate old = stxRepo.findById(1).get();

			old.setName("Azucar");
			old.setTaxrate(new BigDecimal(0.566));
			old.setTaxtype(2);
			String str="2022-03-04 17:37:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			old.setModifieddate(timestamp);

			Salestaxrate saved = stxService.editSalestaxrate(old, 1);

			assertNotEquals(saved,null);

			assertAll(
					() -> assertTrue(saved.getName().equals("Azucar")),
					() -> assertTrue(saved.getRowguid()==3),
					() -> assertTrue(saved.getStateprovince().getStateprovinceid()==1),
					() -> assertTrue(saved.getTaxrate().compareTo(new BigDecimal(0.566))==0),
					() -> assertTrue(saved.getTaxtype()==2)

					);
		}


		@Test
		@Order(5)
		void itEditSales2() throws FailedValidationsException, ElementNotFoundException {
			Salestaxrate old = stxRepo.findById(1).get();

			old.setName("IVA");
			String str="2022-03-04 17:37:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			old.setModifieddate(timestamp);

			assertThrows(FailedValidationsException.class, () ->{
				stxService.editSalestaxrate(old, 1);
			});
		}

		@Test
		@Order(6)
		void itEditSales3() throws FailedValidationsException, ElementNotFoundException {
			Salestaxrate old = stxRepo.findById(1).get();

			old.setTaxrate(new BigDecimal(-0.566));
			String str="2022-03-04 17:37:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			old.setModifieddate(timestamp);

			assertThrows(FailedValidationsException.class, () ->{
				stxService.editSalestaxrate(old, 1);
			});
		}


	}

	@Nested
	@TestMethodOrder(OrderAnnotation.class)
	class AddressTests{

		@Test
		@Order(1)
		void itSaveAddress1() throws FailedValidationsException, ElementNotFoundException {
			Stateprovince sp1 = new Stateprovince();
			sp1.setStateprovinceid(1);
			spRepo.save(sp1);

			Address a = new Address();
			a.setAddressid(1);
			a.setAddressline1("line1");
			a.setAddressline2("line2");
			a.setCity("Cali");
			a.setPostalcode("BO1237");
			a.setRowguid(15);
			a.setSpatiallocation("SL1");

			Address saved = aService.saveAddress(1, a);

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
			a.setAddressid(2);
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
			a.setAddressid(2);
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
			a.setAddressid(2);
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
			a.setAddressid(2);
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
			a.setAddressid(2);
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
			Address old = aRepo.findById(1).get();
			
			old.setAddressline1("linemodified");
			old.setCity("Barranquilla");
			old.setPostalcode("BA1244");
			String str="2022-03-04 18:17:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			old.setModifieddate(timestamp);
			
			Address modified = aService.editAddress(1, old);

			assertNotEquals(modified,null);

			assertAll(
					()-> assertTrue(modified.getAddressline1().equals("linemodified")),
					()-> assertTrue(modified.getCity().equals("Barranquilla")),
					()-> assertTrue(modified.getPostalcode().equals("BA1244"))
					);
			
		}
		

		@Test
		@Order(8)
		void itEditAddress2() throws FailedValidationsException, ElementNotFoundException {
			Address old = aRepo.findById(1).get();
			
			old.setAddressline1("");
			String str="2022-03-04 18:17:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			old.setModifieddate(timestamp);
			
			assertThrows(FailedValidationsException.class, () ->{
				aService.editAddress(1,old);
			});
		}
		
		@Test
		@Order(9)
		void itEditAddress3() throws FailedValidationsException, ElementNotFoundException {
			Address old = aRepo.findById(1).get();

			old.setPostalcode("BA1");
			String str="2022-03-04 18:17:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			old.setModifieddate(timestamp);
			
			assertThrows(FailedValidationsException.class, () ->{
				aService.editAddress(1,old);
			});
		}
		
		@Test
		@Order(10)
		void itEditAddress4() throws FailedValidationsException, ElementNotFoundException {
			Address old = aRepo.findById(1).get();

			old.setPostalcode("BA1111111");
			String str="2022-03-04 18:17:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			old.setModifieddate(timestamp);
			
			assertThrows(FailedValidationsException.class, () ->{
				aService.editAddress(1,old);
			});
		}
		
		@Test
		@Order(10)
		void itEditAddress5() throws FailedValidationsException, ElementNotFoundException {
			Address old = aRepo.findById(1).get();

			old.setCity("Oj");
			String str="2022-03-04 18:17:15"; 
			Timestamp timestamp= Timestamp.valueOf(str);  
			old.setModifieddate(timestamp);
			
			assertThrows(FailedValidationsException.class, () ->{
				aService.editAddress(1,old);
			});
		}


	}



		@Nested
		@TestMethodOrder(OrderAnnotation.class)
		class StateprovinceTest{
			
			void setup() {
				
				st = new Salesterritory();
				st.setTerritoryid(1);
				
				stRepo.save(st);
				//stService.save(st);
				
				country = new Countryregion();
				//country.setCountryregioncode("C11");
				country.setCountryregionid(1);
				//country.setName("Colombia");

				crRepo.save(country);
				
				
			}
			
			@Test
			@Order(1)
			void itSaveState1() throws FailedValidationsException, ElementNotFoundException {
				setup();
				
				Stateprovince sp = new Stateprovince();
				sp.setStateprovinceid(1);
				sp.setStateprovincecode("SCO19");
				sp.setName("Valle del Cauca");
				sp.setIsonlystateprovinceflag("Y/N");
				sp.setTerritoryid(st.getTerritoryid());
				sp.setCountryregion(country);
				
				Stateprovince saved = spService.saveStateprovince(sp, st.getTerritoryid(), country.getCountryregionid());
				
				assertNotEquals(saved,null);
	
				assertAll(
						() -> assertTrue(saved.getName().equals("Valle del Cauca")),
						() -> assertTrue(saved.getStateprovincecode().equals("SCO19")),
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
				Stateprovince old = spRepo.findById(1).get();
				
				old.setStateprovincecode("SCO10");
				old.setName("Guaviare");
				old.setIsonlystateprovinceflag("Y/N");
				String str="2022-04-08 18:43:15"; 
				Timestamp timestamp= Timestamp.valueOf(str);  
				old.setModifieddate(timestamp);
				
				Stateprovince modified = spService.editStateprovince(old, st.getTerritoryid(), country.getCountryregionid());
				
				assertNotEquals(modified,null);

				assertAll(
						()-> assertTrue(modified.getStateprovincecode().equals("SCO10")),
						()-> assertTrue(modified.getName().equals("Guaviare")),
						()-> assertTrue(modified.getIsonlystateprovinceflag().equals("Y/N"))
						);
			}
			
			@Test
			@Order(9)
			void itEditState2() throws FailedValidationsException, ElementNotFoundException {
				setup();
				Stateprovince old = spRepo.findById(1).get();
				
				old.setStateprovincecode("S0");
				String str="2022-04-08 18:43:15"; 
				Timestamp timestamp= Timestamp.valueOf(str);  
				old.setModifieddate(timestamp);
				

				assertThrows(FailedValidationsException.class, () ->{
					spService.editStateprovince(old, st.getTerritoryid(), country.getCountryregionid());
				});
			}
			
			@Test
			@Order(9)
			void itEditState3() throws FailedValidationsException, ElementNotFoundException {
				setup();
				Stateprovince old = spRepo.findById(1).get();
				
				old.setStateprovincecode("S03425235");
				String str="2022-04-08 18:43:15"; 
				Timestamp timestamp= Timestamp.valueOf(str);  
				old.setModifieddate(timestamp);
				

				assertThrows(FailedValidationsException.class, () ->{
					spService.editStateprovince(old, st.getTerritoryid(), country.getCountryregionid());
				});
			}
			
			@Test
			@Order(9)
			void itEditState4() throws FailedValidationsException, ElementNotFoundException {
				setup();
				Stateprovince old = spRepo.findById(1).get();
				
				old.setName("A");
				String str="2022-04-08 18:43:15"; 
				Timestamp timestamp= Timestamp.valueOf(str);  
				old.setModifieddate(timestamp);
				

				assertThrows(FailedValidationsException.class, () ->{
					spService.editStateprovince(old, st.getTerritoryid(), country.getCountryregionid());
				});
			}
		}





}
