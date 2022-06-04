package co.edu.icesi.dev.uccareapp.transport.delegate;

import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;

public interface CountryregionDelegate {

	public Iterable<Countryregion> findAll();

	public void save(Countryregion cr);
	
	public Countryregion edit(Countryregion countryregion);

	public Countryregion findById(Integer id);

}
