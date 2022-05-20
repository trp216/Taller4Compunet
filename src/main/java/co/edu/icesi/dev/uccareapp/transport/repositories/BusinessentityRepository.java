package co.edu.icesi.dev.uccareapp.transport.repositories;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.dev.uccareapp.transport.model.person.Address;
import co.edu.icesi.dev.uccareapp.transport.model.person.Businessentity;

public interface BusinessentityRepository extends CrudRepository<Businessentity, Integer>{

}
