package co.edu.icesi.dev.uccareapp.transport.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserType;

@Repository
public interface UserRepository  extends CrudRepository<UserApp, Long>{
	
//List<User> findByUsername(String username);
	
	List<UserApp> findByType(UserType type);
	
	UserApp findByUsername(String username);

}
