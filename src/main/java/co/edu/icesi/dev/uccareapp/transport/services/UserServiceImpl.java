package co.edu.icesi.dev.uccareapp.transport.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserType;
import co.edu.icesi.dev.uccareapp.transport.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public void save(UserApp user) {
//		if(user.getNewPassword()== null) {
//			user.setPassword(findById(user.getId()).get().getPassword());
//			save(user);
//		}else {
//			if(findById(user.getId()).get().getPassword().equals(user.getOldPassword())) {
//				Optional<User> user1 = findById(user.getId());
//		        user1.get().setOldPassword(user.getOldPassword());
//				user1.get().setPassword(user.getNewPassword());
//				
//				System.out.println(user1.get().getPassword());
//				
//			save(user);
//				
//			}
		userRepository.save(user);
		//}
	}

	public Optional<UserApp> findById(long id) {

		return userRepository.findById(id);
	}

	public Iterable<UserApp> findAll() {
		return userRepository.findAll();
	}
	
	public Iterable<UserApp> findAllAdmins() {
		return userRepository.findByType(UserType.admin);
	}
	
	
	public Iterable<UserApp> findAllOperators() {
		return userRepository.findByType(UserType.operator);
	}


	public void delete(UserApp user) {
		userRepository.delete(user);

	}


	public UserType[] getTypes() {
		// TODO Auto-generated method stub
		return UserType.values();
	}

}
