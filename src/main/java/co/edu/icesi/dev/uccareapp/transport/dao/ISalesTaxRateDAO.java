package co.edu.icesi.dev.uccareapp.transport.dao;

import java.util.List;

import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;


public interface ISalesTaxRateDAO {
	
	public Salestaxrate save(Salestaxrate entity);
	public Salestaxrate update(Salestaxrate entity);
	public void delete(Salestaxrate entity);
	public Salestaxrate findById(Integer codigo);
	public List<Salestaxrate> findAll();

}
