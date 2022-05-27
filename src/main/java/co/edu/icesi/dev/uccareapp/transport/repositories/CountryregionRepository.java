package co.edu.icesi.dev.uccareapp.transport.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;

@Repository
public interface CountryregionRepository extends CrudRepository<Countryregion, Integer>{

}
