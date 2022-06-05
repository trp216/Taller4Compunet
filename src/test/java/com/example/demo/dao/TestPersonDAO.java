package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.uccareapp.transport.Application;
import co.edu.icesi.dev.uccareapp.transport.dao.EmployeeDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.PersonDAO;
import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;
import co.edu.icesi.dev.uccareapp.transport.model.person.Person;

@ContextConfiguration(classes = Application.class)
@SpringBootTest
public class TestPersonDAO {
    @Autowired
    private PersonDAO pDAO;

    @Autowired
    private EmployeeDAO eDAO;

    private Employee employee;
    private Person person;

    void initDAO(){
        Employee e = new Employee();
        Person p = new Person();
        eDAO.save(e);
        pDAO.save(p);

        employee = eDAO.findById(1);
        person = pDAO.findById(1);

        employee.setPersonid(person.getBusinessentityid());
        person.setEmployeeId(employee.getBusinessentityid());
        
        employee = eDAO.update(employee);
        System.out.println("Final employee: "+employee);
        person = pDAO.update(person);
        System.out.println("Final Person: "+person);
    }

    @Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    void testFindByEmployeeId(){
        initDAO();
        Person found = pDAO.findByEmployeeId(employee.getBusinessentityid());
        assertNotNull(found);
        assertEquals(person, found);
    }

    @Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    void testFindByPersonId(){
        initDAO();
        Employee found = eDAO.findByPersonId(person.getBusinessentityid());
        assertNotNull(found);
        assertEquals(employee, found);
    }
}
