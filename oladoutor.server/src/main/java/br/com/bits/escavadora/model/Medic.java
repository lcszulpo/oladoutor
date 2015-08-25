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
import br.com.bits.escavadora.model.MedicDocument;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import br.com.bits.escavadora.model.MedicAddress;
import javax.persistence.FetchType;
@Entity
@Table(name = "medic")
public class Medic implements Serializable {

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

	@OneToMany(mappedBy = "medic", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MedicDocument> medicDocument = new HashSet<MedicDocument>();

	@OneToMany(mappedBy = "medic", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<MedicAddress> medicAddresses = new HashSet<MedicAddress>();

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
		if (!(obj instanceof Medic)) {
			return false;
		}
		Medic other = (Medic) obj;
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

	public Set<MedicDocument> getMedicDocument() {
		return this.medicDocument;
	}

	public void setMedicDocument(final Set<MedicDocument> medicDocument) {
		this.medicDocument = medicDocument;
	}

	public Set<MedicAddress> getMedicAddresses() {
		return this.medicAddresses;
	}

	public void setMedicAddresses(final Set<MedicAddress> medicAddresses) {
		this.medicAddresses = medicAddresses;
	}
}