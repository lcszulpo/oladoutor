package br.com.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "patient")
@XmlRootElement
public class Patient implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column(length = 70, name = "name", nullable = false)
	private String name;

	@Column(length = 70, name = "lastName", nullable = false)
	private String lastName;

	@Column(name = "age", nullable = false)
	private Integer age;

	@Column(name = "age_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private AgeType ageType;

	@Column(name = "sex", nullable = false)
	@Enumerated(EnumType.STRING)
	private Sex sex;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Locale locale;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	private Set<Encounter> encounters = new HashSet<Encounter>();

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public AgeType getAgeType() {
		return ageType;
	}

	public void setAgeType(AgeType ageType) {
		this.ageType = ageType;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Locale getLocale() {
		return this.locale;
	}

	public void setLocale(final Locale locale) {
		this.locale = locale;
	}

	public Set<Encounter> getEncounters() {
		return this.encounters;
	}

	public void setEncounters(final Set<Encounter> encounters) {
		this.encounters = encounters;
	}

	public enum AgeType {
		YEARS, MONTHS
	}

	public enum Sex {
		M, F
	}

	public enum Status {
		ACTIVE, INACTIVE
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
		if (age != null)
			result += ", age: " + age;
		if (ageType != null)
			result += ", ageType: " + ageType;
		if (sex != null)
			result += ", sex: " + sex;
		if (locale != null)
			result += ", locale: " + locale;
		if (encounters != null)
			result += ", encounters: " + encounters;
		if (status != null)
			result += ", status: " + status;
		return result;
	}

}