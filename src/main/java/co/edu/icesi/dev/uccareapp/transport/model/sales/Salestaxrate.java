package co.edu.icesi.dev.uccareapp.transport.model.sales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import co.edu.icesi.dev.uccareapp.transport.model.person.Stateprovince;
import co.edu.icesi.dev.uccareapp.transport.validation.Miracle;

/**
 * The persistent class for the salestaxrate database table.
 *
 */
@Entity
@NamedQuery(name = "Salestaxrate.findAll", query = "SELECT s FROM Salestaxrate s")
public class Salestaxrate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SALESTAXRATE_SALESTAXRATEID_GENERATOR", allocationSize = 1, sequenceName = "SALESTAXRATE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SALESTAXRATE_SALESTAXRATEID_GENERATOR")
	private Integer salestaxrateid;

	private Timestamp modifieddate;

	@Size(min=5,groups=Miracle.class)
	private String name;

	private Integer rowguid;

	@NotNull(groups = Miracle.class)
	@ManyToOne
	@JoinColumn(name = "stateprovinceid")
	private Stateprovince stateprovince;

	@PositiveOrZero(groups=Miracle.class)
	//@Pattern(regexp="^(0|[1-9][0-9]*)$",groups=Miracle.class)
	private BigDecimal taxrate;

	private Integer taxtype;

	public Salestaxrate() {
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

	public Integer getSalestaxrateid() {
		return this.salestaxrateid;
	}

	public Stateprovince getStateprovince() {
		return this.stateprovince;
	}

	public BigDecimal getTaxrate() {
		return this.taxrate;
	}

	public Integer getTaxtype() {
		return this.taxtype;
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

	public void setSalestaxrateid(Integer salestaxrateid) {
		this.salestaxrateid = salestaxrateid;
	}

	public void setStateprovince(Stateprovince stateprovince) {
		this.stateprovince = stateprovince;
	}

	public void setTaxrate(BigDecimal taxrate) {
		this.taxrate = taxrate;
	}

	public void setTaxtype(Integer taxtype) {
		this.taxtype = taxtype;
	}

}