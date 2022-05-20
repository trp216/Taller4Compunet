package co.edu.icesi.dev.uccareapp.transport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;

@Repository
public interface SalesTerritoryRepository extends CrudRepository<Salesterritory, Integer>{

}
