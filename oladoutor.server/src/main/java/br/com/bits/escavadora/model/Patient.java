package br.com.bits.escavadora.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import br.com.bits.escavadora.model.PatientAddress;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import br.com.bits.escavadora.model.PatientContact;
import br.com.bits.escavadora.model.PatientDocument;
import br.com.bits.escavadora.model.Appointment;
@Entity
@Table(name = "patient")
public class Patient implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "birthday")
	@Temporal(TemporalType.DATE)
	private Date birthday;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<PatientAddress> patientAddresses = new HashSet<PatientAddress>();

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<PatientContact> patientContacts = new HashSet<PatientContact>();

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<PatientDocument> patientDocuments = new HashSet<PatientDocument>();

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	private Set<Appointment> appointments = new HashSet<Appointment>();

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
		if (!(obj instanceof Patient)) {
			return false;
		}
		Patient other = (Patient) obj;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (name != null && !name.trim().isEmpty())
			result += ", name: " + name;
		if (lastName != null && !lastName.trim().isEmpty())
			result += ", lastName: " + lastName;
		if (birthday != null)
			result += ", birthday: " + birthday;
		return result;
	}

	public Set<PatientAddress> getPatientAddresses() {
		return this.patientAddresses;
	}

	public void setPatientAddresses(final Set<PatientAddress> patientAddresses) {
		this.patientAddresses = patientAddresses;
	}

	public Set<PatientContact> getPatientContacts() {
		return this.patientContacts;
	}

	public void setPatientContacts(final Set<PatientContact> patientContacts) {
		this.patientContacts = patientContacts;
	}

	public Set<PatientDocument> getPatientDocuments() {
		return this.patientDocuments;
	}

	public void setPatientDocuments(final Set<PatientDocument> patientDocuments) {
		this.patientDocuments = patientDocuments;
	}

	public Set<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(final Set<Appointment> appointments) {
		this.appointments = appointments;
	}
}