package co.edu.icesi.dev.uccareapp.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;
import co.edu.icesi.dev.uccareapp.transport.services.SalestaxrateServiceImp;
import co.edu.icesi.dev.uccareapp.transport.services.StateprovinceServiceImp;

@RestController
public class SalestaxrateRestController {
	
	@Autowired 
	private SalestaxrateServiceImp strService;
	
	@Autowired 
	private StateprovinceServiceImp spService;
	
	@RequestMapping(value = "/api/tax/", method = RequestMethod.GET)
	public Iterable<Salestaxrate> findAll() {
		return strService.findAll();
	}
	
	@RequestMapping(value = "/api/tax/", method = RequestMethod.POST)
	public void saveSTR(@RequestBody Salestaxrate str) {
		try {
			strService.save(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/api/tax/", method = RequestMethod.PUT)
	public Salestaxrate editSTR(@RequestBody Salestaxrate str) {
		
		try {
			if(str.getStateprovince()!=null) {
				return strService.edit(str,str.getStateprovince().getStateprovinceid());
			}
			else {
				return strService.edit(str,null);

			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return str;
		}
	}
	
	@RequestMapping(value = "/api/tax/{id}", method = RequestMethod.GET)
	public Salestaxrate findById(@PathVariable("id") Integer id) {
		return strService.findById(id);
	}
	
	@RequestMapping(value = "/api/tax/stateprovinces", method = RequestMethod.GET)
	public Iterable<Stateprovince> getStateprovinces() {
		
		return spService.findAll();
	}

}
