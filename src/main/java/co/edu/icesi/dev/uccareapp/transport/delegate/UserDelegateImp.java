package co.edu.icesi.dev.uccareapp.transport.delegate;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserType;

@Component
public class UserDelegateImp implements UserDelegate{

	public final static String PATH = "http://localhost:8080/api/user/";

	private RestTemplate rest;

	public UserDelegateImp() {
		rest = new RestTemplate();
	}

	@Override
	public void save(UserApp user) {
		// TODO Auto-generated method stub
		rest.postForEntity(PATH, user, UserApp.class);
	}

	@Override
	public UserApp findById(long id) {
		// TODO Auto-generated method stub
		return rest.getForObject(PATH+id, UserApp.class);
	}

	@Override
	public Iterable<UserApp> findAll() {
		// TODO Auto-generated method stub
		UserApp[] users = rest.getForObject(PATH, UserApp[].class);
		return Arrays.asList(users);
	}

	@Override
	public Iterable<UserApp> findAllAdmins() {
		// TODO Auto-generated method stub
		UserApp[] users = rest.getForObject(PATH+"admins/", UserApp[].class);
		return Arrays.asList(users);
	}

	@Override
	public Iterable<UserApp> findAllOperators() {
		// TODO Auto-generated method stub
		UserApp[] users = rest.getForObject(PATH+"operators/", UserApp[].class);
		return Arrays.asList(users);
	}

	@Override
	public void delete(UserApp user) {
		// TODO Auto-generated method stub
		rest.delete(PATH+ user.getId());
	}

	@Override
	public UserType[] getTypes() {
		// TODO Auto-generated method stub
		return rest.getForObject(PATH+"types/", UserType[].class);
	}

}
