package co.edu.icesi.dev.uccareapp.transport.delegate;

import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;

public interface SalestaxrateDelegate {
	
	public Salestaxrate edit(Salestaxrate str);
	
	public Salestaxrate findById(Integer id);

	public Iterable<Salestaxrate> findAll();
	
	public void save(Salestaxrate str);

}
