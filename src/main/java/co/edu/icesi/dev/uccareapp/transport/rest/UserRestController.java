package co.edu.icesi.dev.uccareapp.transport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserType;
import co.edu.icesi.dev.uccareapp.transport.services.UserServiceImpl;

@RestController
public class UserRestController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value = "/api/user/", method = RequestMethod.GET)
	public Iterable<UserApp> findAll() {
		return userService.findAll();
	}

	@RequestMapping(value = "/api/user/", method = RequestMethod.POST)
	public void saveUser(@RequestBody UserApp user) {
		try {
			userService.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET)
	public UserApp findById(@PathVariable("id") long id) {
		return userService.findById(id);
	}
	
	
	@RequestMapping(value = "/api/user/admins/", method = RequestMethod.GET)
	public Iterable<UserApp> findAllAdmins() {
		return userService.findAllAdmins();
	}
	
	@RequestMapping(value = "/api/user/operators/", method = RequestMethod.GET)
	public Iterable<UserApp> findAllOperators() {
		return userService.findAllOperators();
	}
	
	@RequestMapping(value = "/api/user/{id}", method = RequestMethod.DELETE)
	public void deleteGame(@PathVariable ("id") long id) {
		UserApp toErase = userService.findById(id);
		if(toErase!=null) {
			userService.delete(toErase);
		}
		
	}
	
	@RequestMapping(value = "/api/user/types/", method = RequestMethod.GET)
	public UserType[] findTypes() {
		return userService.getTypes();
	}
	
}
