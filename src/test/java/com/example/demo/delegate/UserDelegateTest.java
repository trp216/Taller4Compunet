package com.example.demo.delegate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.delegate.UserDelegateImp;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserApp;
import co.edu.icesi.dev.uccareapp.transport.model.user.UserType;

@ContextConfiguration(classes = Application.class)
@SpringBootTest
public class UserDelegateTest {
	
	@Mock
	RestTemplate rest;
	
	@InjectMocks
	UserDelegateImp userDelegate;
	UserApp u1;
	UserApp u2;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void  setup() {
		MockitoAnnotations.initMocks(this);
		
		u1 = new UserApp();
		u1.setId(1);
    	u1.setPassword("{noop}1234567");
    	u1.setType(UserType.admin);
    	u1.setUsername("taylor99");
    	
	}
	
	public void setup2() {
		Mockito.when(rest.postForEntity("http://localhost:8080/api/user/", u1,UserApp.class))
		.thenReturn(new ResponseEntity<>(u1,HttpStatus.OK));
		
		userDelegate.save(u1);
	}

	public void setup3() {

    	u2 = new UserApp();
    	u2.setId(2);
    	u2.setPassword("{noop}panza2457");
    	u2.setType(UserType.operator);
    	u2.setUsername("troubletrouble");
		
		
		Mockito.when(rest.postForEntity("http://localhost:8080/api/user/", u2,UserApp.class))
		.thenReturn(new ResponseEntity<>(u2,HttpStatus.OK));
		
		userDelegate.save(u2);
	}

	@Test
	public void testSave() {
		Mockito.when(rest.postForEntity("http://localhost:8080/api/user/", u1,UserApp.class))
		.thenReturn(new ResponseEntity<>(u1,HttpStatus.OK));
		
		userDelegate.save(u1);
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/user/1",UserApp.class))
		.thenReturn(new ResponseEntity<UserApp>(u1,HttpStatus.OK).getBody());
		
		
		assertEquals(userDelegate.findById(1).getUsername(), "taylor99");
	}
	
	@Test
	public void testFindById() {
		
		setup2();

		Mockito.when(rest.getForObject("http://localhost:8080/api/user/1",UserApp.class))
		.thenReturn(new ResponseEntity<UserApp>(u1,HttpStatus.OK).getBody());
	
		UserApp found = userDelegate.findById(1);
		
		assertEquals(found.getUsername(), "taylor99");
		
	}
	
	@Test
	public void findAllTest() {
		setup2();
		setup3();
		
		UserApp[] users = {u1,u2};
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/user/",UserApp[].class))
		.thenReturn(new ResponseEntity<UserApp[]>(users,HttpStatus.OK).getBody());
		
		Iterable<UserApp> usersResult = userDelegate.findAll();
		assertNotNull(usersResult);

		String usernames ="";
		for (UserApp t: usersResult) {
			usernames+= t.getUsername()+" ";
		}	
		
		assertEquals(usernames,"taylor99 troubletrouble ");

	}
	
	@Test
	public void deleteTest() {
		setup2();		
		userDelegate = mock(UserDelegateImp.class);
		 
		Mockito.doNothing().when(userDelegate).delete(u1);		
		
		Mockito.when(rest.getForObject("http://localhost:8080/api/user/1",UserApp.class))
		.thenReturn(new ResponseEntity<UserApp>(u1, HttpStatus.OK).getBody());		
		
		UserApp saved = userDelegate.findById(1);
		assertNull(saved);
	}

}
