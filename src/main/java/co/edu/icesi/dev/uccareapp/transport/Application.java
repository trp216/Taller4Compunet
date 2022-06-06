package co.edu.icesi.dev.uccareapp.transport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.dev.uccareapp.transport.dao.AddressDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.CountryRegionDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.SalesTaxRateDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.SalesTerritoryDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.StateProvinceDAO;
import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesorderheader;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserType;
import co.edu.icesi.dev.uccareapp.transport.repositories.SalesorderheadersRepository;
import co.edu.icesi.dev.uccareapp.transport.repositories.UserRepository;

//Uncomment to test:
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableJpaRepositories("com.example.demo.repositories")
//@EnableAutoConfiguration
//@EntityScan(basePackages= {"com.example.demo.model","co.edu.icesi.dev.uccareapp.transport.model.person","co.edu.icesi.dev.uccareapp.transport.model.sales", "co.edu.icesi.dev.uccareapp.transport.model.hr"})
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
	    	
	    	addressRepository.save(a1);
	    	
	    	Salestaxrate str = new Salestaxrate();
	    	str.setName("Aranceles");
	    	str.setTaxrate(new BigDecimal(15));
	    	str.setStateprovince(sp1);
	    	
	    	strRepository.save(str);
	    	
//	    	
//	    	Address a2 = new Address();
//	    	a1.setAddressline1("Line 1 of address");
//	    	a1.setCity("Bogota");
//	    	a1.setAddressline2("Line 2 of address");
//	    	a1.setPostalcode("A12346");
//	    	a1.setSpatiallocation("Comuna 2");
//	    	addressRepository.save(a2);
	    	
	    	Stateprovince sp2 = new Stateprovince();
	    	sp2.setName("Cundinamarca");
	    	sp2.setStateprovincecode("12346");
	    	sp2.setCountryregion(cr);
	    	
	    	ArrayList<Address> ads = new ArrayList<Address>();
	    	ads.add(a1);
	    	sp2.setAddresses(ads);
	    	stateprovinceRepository.save(sp2);
	    	
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
