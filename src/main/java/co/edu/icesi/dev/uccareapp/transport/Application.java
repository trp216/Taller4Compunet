package co.edu.icesi.dev.uccareapp.transport;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.dev.uccareapp.transport.dao.AddressDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.CountryRegionDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.EmployeeDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.PersonDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.SalesTaxRateDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.SalesTerritoryDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.StateProvinceDAO;
import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Person;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesorderheader;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserType;
import co.edu.icesi.dev.uccareapp.transport.repositories.SalesorderheadersRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.UserRepository;

//Uncomment to test:
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableJpaRepositories("com.example.demo.repositories")
//@EnableAutoConfiguration
//@EntityScan(basePackages= {"com.example.demo.model","co.edu.icesi.dev.uccareapp.transport.model.person","co.edu.icesi.dev.uccareapp.transport.model.sales", "co.edu.icesi.dev.uccareapp.transport.model.hr"})

//Uncomment to test
//@ComponentScan(basePackages = {"co.edu.icesi.dev.uccareapp.transport.controller","co.edu.icesi.dev.uccareapp.transport.dao","co.edu.icesi.dev.uccareapp.transport.delegate","co.edu.icesi.dev.uccareapp.transport.repositories","co.edu.icesi.dev.uccareapp.transport.rest","co.edu.icesi.dev.uccareapp.transport.services","co.edu.icesi.dev.uccareapp.transport.validation"} )
public class Application {

	@Bean

	public CommandLineRunner dummy(UserRepository userRepository
			,AddressDAO addressRepository, 
			StateProvinceDAO stateprovinceRepository 
			,SalesTaxRateDAO strRepository
			,CountryRegionDAO crRepository
			,SalesTerritoryDAO territoryRepository
			,SalesorderheadersRepository sohRepository
			,PersonDAO pDAO
			,EmployeeDAO eDAO
			){

		//para cerrar sesion:
		//http://localhost:8081/logout
		return (args) -> {
			//UserServiceImpl registration = new UserServiceImpl(userRepository);

			//mientras no haya implementado lo del ultimo taller, para entrar usar las credenciales
			//username: user
			//password: la que salga generada por consola

			UserApp u = new UserApp();
			u.setPassword("{noop}1234567");
			u.setType(UserType.admin);
			u.setUsername("peppa1234");
			//u.setOldPassword("{noop}123456789");
			userRepository.save(u);

			UserApp u2 = new UserApp();
			u2.setPassword("{noop}1234");
			u2.setType(UserType.operator);
			u2.setUsername("dora1234");

			userRepository.save(u2);


			Countryregion cr = new Countryregion();
			cr.setName("Colombia");
			cr.setCountryregioncode("C12");

			crRepository.save(cr);

			Salesterritory territory = new Salesterritory();
			territory.setName("Zona del pacifico");


			territoryRepository.save(territory);


			Stateprovince sp1 = new Stateprovince();
			sp1.setName("Valle del Cauca");
			sp1.setStateprovincecode("12345");
			sp1.setCountryregion(cr);
			sp1.setTerritoryid(territory.getTerritoryid());

			stateprovinceRepository.save(sp1);


			Address a1 = new Address();
			a1.setAddressline1("Line 1 of address");
			a1.setCity("Cali");
			a1.setAddressline2("Line 2 of address");
			a1.setPostalcode("A12345");
			a1.setSpatiallocation("Comuna 2");
			a1.setStateprovince(sp1);
			List<Salesorderheader> soh = new ArrayList<Salesorderheader>();

			Salesorderheader s1 = new Salesorderheader();
			sohRepository.save(s1);
			Salesorderheader s2 = new Salesorderheader();
			sohRepository.save(s2);
			Salesorderheader s3 = new Salesorderheader();
			sohRepository.save(s3);

			soh.add(s1);
			soh.add(s2);
			soh.add(s3);

			a1.setSalesorderheaders(soh);
			//	System.out.println(">>>>>>Size of arraylist: "+a1.getSalesorderheaders().size());
			//	System.out.println(">>>>>>SOH count"+a1.getSohcount());

			addressRepository.save(a1);

			Salestaxrate str = new Salestaxrate();
			str.setName("Aranceles");
			str.setTaxrate(new BigDecimal(15));
			str.setStateprovince(sp1);

			strRepository.save(str);
///////////////////////////////////////////////////////////

			Address a2 = new Address();
			a2.setAddressline1("Line 1 of address");
			a2.setCity("Bogota");
			a2.setAddressline2("Line 2 of address");
			a2.setPostalcode("A12346");
			a2.setSpatiallocation("Comuna 2");

			List<Salesorderheader> soh2 = new ArrayList<Salesorderheader>();

			Salesorderheader s4 = new Salesorderheader();
			sohRepository.save(s4);
			
			soh2.add(s4);
			a2.setSalesorderheaders(soh2);
			a2.setStateprovince(sp1);

			addressRepository.save(a2);

			Stateprovince sp2 = new Stateprovince();
			sp2.setName("Cundinamarca");
			sp2.setStateprovincecode("12346");
			sp2.setCountryregion(cr);

			ArrayList<Address> ads = new ArrayList<Address>();
			ads.add(a1);
			sp2.setAddresses(ads);

			stateprovinceRepository.save(sp2);

////////////////////////////////////////////////////////////////
			
			Countryregion cr2 = new Countryregion();
			cr2.setName("Estados Unidos");
			cr2.setCountryregioncode("U07");

			crRepository.save(cr2);
			
			Stateprovince sp3 = new Stateprovince();
			sp3.setName("Alabama");
			sp3.setStateprovincecode("12333");
			sp3.setCountryregion(cr2);
			
			
			ArrayList<Address> ads3 = new ArrayList<Address>();
			
			sp3.setAddresses(ads3);

			stateprovinceRepository.save(sp3);
			
			Address a3 = new Address();
			a3.setAddressline1("Line 1 of address");
			a3.setCity("Montgomery");
			a3.setAddressline2("Line 2 of address");
			a3.setPostalcode("A12147");
			a3.setSpatiallocation("Comuna 2");

			
			a3.setSalesorderheaders(soh);
			a3.setStateprovince(sp3);

			addressRepository.save(a3);
			
			Address a4 = new Address();
			a4.setAddressline1("Line 1 of address");
			a4.setCity("Birmingham");
			a4.setAddressline2("Line 2 of address");
			a4.setPostalcode("A12148");
			a4.setSpatiallocation("Comuna 4");

			
			a4.setSalesorderheaders(soh);
			a4.setStateprovince(sp3);

			addressRepository.save(a4);
			
			sp3.getAddresses().add(a3);
			sp3.getAddresses().add(a4);
			
			stateprovinceRepository.update(sp3);

			////////////////////////////////
			Person p = new Person();
			p.setAdditionalcontactinfo("N/A");
			p.setDemographics("Mestizo");
			p.setEmailpromotion(1);
			p.setFirstname("Juanito");
			p.setLastname("Perez");
			p.setMiddlename("Francisco");
			p.setNamestyle("N/A");
			p.setPersontype("Empleado");
			p.setSuffix("Suffix");
			p.setTitle("Director");

			pDAO.save(p);

			Employee e = new Employee();
			e.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse("21/06/2000"));
			e.setCurrentflag("Flag 1");
			e.setGender("Masculino");
			e.setHiredate(new SimpleDateFormat("dd/MM/yyyy").parse("10/08/2015"));
			e.setJobtitle("Ingeniero");
			e.setLoginid("Loginid");
			e.setMaritalstatus("Casado");
			e.setNationalidnumber("123456789");
			e.setOrganizationnode("Logistica");
			e.setSalariedflag("Y");
			e.setSickleavehours(8);
			e.setVacationhours(10);

			eDAO.save(e);
			Person pFound = pDAO.findById(1);
			Employee eFound = eDAO.findById(1);
			pFound.setEmployeeId(eFound.getBusinessentityid());
			eFound.setPersonid(pFound.getEmployeeId());
			pDAO.update(pFound);
			eDAO.update(eFound);
		};


	}

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	// Alejandra Diaz Parra
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
