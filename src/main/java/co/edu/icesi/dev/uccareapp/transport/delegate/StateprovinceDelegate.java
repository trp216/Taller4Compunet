package co.edu.icesi.dev.uccareapp.transport.delegate;

import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;

public interface StateprovinceDelegate {

	public Iterable<Stateprovince> findAll();

	public Stateprovince findById(Integer id);

	public void save(Stateprovince sp);

	//public Stateprovince edit(Stateprovince sp, Integer countryregionid);

	public Stateprovince edit(Stateprovince sp);
}
