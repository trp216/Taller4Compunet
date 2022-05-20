package co.edu.icesi.dev.uccareapp.transport.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserType;

@Service
public interface UserService {
	
	public void save(UserApp user);

	public Optional<UserApp> findById(long id);

	public Iterable<UserApp> findAll();

	public Iterable<UserApp> findAllAdmins();

	public Iterable<UserApp> findAllOperators();

	public void delete(UserApp user);


	public UserType[] getTypes();

}
