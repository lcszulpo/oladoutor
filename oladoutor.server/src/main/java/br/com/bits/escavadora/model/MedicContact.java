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
import br.com.bits.escavadora.model.MedicContactType;
import javax.persistence.EnumType;
@Entity
@Table(name = "medic_contact")
public class MedicContact implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column(name = "description", nullable = false)
	private String description;

	@Enumerated(EnumType.STRING)
	private MedicContactType medicContactType;

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
		if (!(obj instanceof MedicContact)) {
			return false;
		}
		MedicContact other = (MedicContact) obj;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MedicContactType getMedicContactType() {
		return medicContactType;
	}

	public void setMedicContactType(MedicContactType medicContactType) {
		this.medicContactType = medicContactType;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (description != null && !description.trim().isEmpty())
			result += ", description: " + description;
		if (medicContactType != null)
			result += ", medicContactType: " + medicContactType;
		return result;
	}
}