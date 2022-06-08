package co.edu.icesi.dev.uccareapp.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.uccareapp.transport.services.AddressServiceImp;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Person;
import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.services.StateprovinceServiceImp;

@RestController
public class AddressRestController {
	
	@Autowired
	private AddressServiceImp adService;
	
	@Autowired 
	private StateprovinceServiceImp spService;
	
	@RequestMapping(value = "/api/address/", method = RequestMethod.GET)
	public Iterable<Address> findAll() {
		return adService.findAll();
	}

	@RequestMapping(value = "/api/address/", method = RequestMethod.POST)
	public void saveAddress(@RequestBody Address address) {
		try {
			adService.save(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/api/address/", method = RequestMethod.PUT)
	public Address editAddress(@RequestBody Address address) {
		
		try {
			if(address.getStateprovince()!=null) {
				return adService.edit(address,address.getStateprovince().getStateprovinceid());
			}
			else {
				return adService.edit(address,null);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return address;
		}
	}
	
	@RequestMapping(value = "/api/address/{id}", method = RequestMethod.GET)
	public Address findById(@PathVariable("id") Integer id) {
		return adService.findById(id);
	}
	
	@RequestMapping(value = "/api/address/stateprovinces", method = RequestMethod.GET)
	public Iterable<Stateprovince> getStateprovinces() {
		
		return spService.findAll();
	}
	
	 @RequestMapping(value="/api/address/stateprovince/{id}", method=RequestMethod.GET)
	    public Address findByStateprovince(@PathVariable("id") Integer id){
	        return adService.findByStateprovince(id);
	    }
}
