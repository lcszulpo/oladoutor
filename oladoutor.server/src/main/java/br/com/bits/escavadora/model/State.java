package br.com.bits.escavadora.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.util.Set;
import java.util.HashSet;
import br.com.bits.escavadora.model.City;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
@Entity
@Table(name = "state")
public class State implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column(name = "name", nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "federative_unit", nullable = false)
	private FederativeUnit federativeUnit;

	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
	private Set<City> cities = new HashSet<City>();

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof State)) {
			return false;
		}
		State other = (State) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FederativeUnit getFederativeUnit() {
		return federativeUnit;
	}

	public void setFederativeUnit(FederativeUnit federativeUnit) {
		this.federativeUnit = federativeUnit;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (name != null && !name.trim().isEmpty())
			result += ", name: " + name;
		if (federativeUnit != null)
			result += ", federativeUnit: " + federativeUnit;
		return result;
	}

	public Set<City> getCities() {
		return this.cities;
	}

	public void setCities(final Set<City> cities) {
		this.cities = cities;
	}
}