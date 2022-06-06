package co.edu.icesi.dev.uccareapp.transport.model.person;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.edu.icesi.dev.uccareapp.transport.model.sales.Salestaxrate;
import co.edu.icesi.dev.uccareapp.transport.validation.Miracle;
import lombok.ToString;

/**
 * The persistent class for the stateprovince database table.
 *
 */
@ToString
@Entity
@NamedQuery(name = "Stateprovince.findAll", query = "SELECT s FROM Stateprovince s")
public class Stateprovince implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "STATEPROVINCE_STATEPROVINCEID_GENERATOR", allocationSize = 1, sequenceName = "STATEPROVINCE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STATEPROVINCE_STATEPROVINCEID_GENERATOR")
	private Integer stateprovinceid;

	private String isonlystateprovinceflag;

	private Timestamp modifieddate;

	@Size(min=5,groups=Miracle.class)
	private String name;

	private Integer rowguid;
	
	private Integer adcount;

	@Size(min=5,max=5,groups=Miracle.class)
	@Pattern(regexp="^(0|[1-9][0-9]*)$")
	private String stateprovincecode;

	private Integer territoryid;

	// bi-directional many-to-one association to Address
	@OneToMany(mappedBy = "stateprovince")
	@JsonIgnore
	private List<Address> addresses;

	// bi-directional many-to-one association to Countryregion
	@ManyToOne
	@JoinColumn(name = "countryregioncode")
	//@JsonIgnore
	private Countryregion countryregion;
	
	@OneToMany
	@JoinColumn(name = "stateprovince")
	private List<Salestaxrate> salestaxrates;

	public Stateprovince() {
		
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setStateprovince(this);

		return address;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	
	
	public Countryregion getCountryregion() {
		return this.countryregion;
	}

	public String getIsonlystateprovinceflag() {
		return this.isonlystateprovinceflag;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public String getName() {
		return this.name;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public String getStateprovincecode() {
		return this.stateprovincecode;
	}

	public Integer getStateprovinceid() {
		return this.stateprovinceid;
	}

	public Integer getTerritoryid() {
		return this.territoryid;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setStateprovince(null);

		return address;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void setCountryregion(Countryregion countryregion) {
		this.countryregion = countryregion;
	}

	public void setIsonlystateprovinceflag(String isonlystateprovinceflag) {
		this.isonlystateprovinceflag = isonlystateprovinceflag;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public void setStateprovincecode(String stateprovincecode) {
		this.stateprovincecode = stateprovincecode;
	}

	public void setStateprovinceid(Integer stateprovinceid) {
		this.stateprovinceid = stateprovinceid;
	}

	public void setTerritoryid(Integer territoryid) {
		this.territoryid = territoryid;
	}

	public Integer getAdcount() {
		return adcount;
	}

	public void setAdcount(Integer adCount) {
		this.adcount = adCount;
	}

}