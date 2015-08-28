package br.com.bits.escavadora.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import br.com.bits.escavadora.model.Patient;
import javax.persistence.ManyToOne;
import br.com.bits.escavadora.model.City;
@Entity
@Table(name = "patient_address")
public class PatientAddress implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@ManyToOne
	private Patient patient;

	@Column(name = "street")
	private String street;

	@Column(name = "number")
	private String number;

	@ManyToOne
	private City city;

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
		if (!(obj instanceof PatientAddress)) {
			return false;
		}
		PatientAddress other = (PatientAddress) obj;
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

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(final Patient patient) {
		this.patient = patient;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (patient != null)
			result += ", patient: " + patient;
		if (street != null && !street.trim().isEmpty())
			result += ", street: " + street;
		if (number != null && !number.trim().isEmpty())
			result += ", number: " + number;
		return result;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(final City city) {
		this.city = city;
	}
}