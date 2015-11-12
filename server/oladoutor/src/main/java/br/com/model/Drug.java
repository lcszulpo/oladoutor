package br.com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@XmlRootElement
public class Drug implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	@Version
	@Column(name = "version")
	private int version;

	@Column(length = 70, name = "description", nullable = false)
	private String description;

	@Column(name = "administration_route")
	@Enumerated(EnumType.STRING)
	private AdministrationRoute administrationRoute;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
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
		if (!(obj instanceof Drug)) {
			return false;
		}
		Drug other = (Drug) obj;
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

	public enum AdministrationRoute {
		TOPICAL, //TÓPICA  efeito local; a substância é aplicada diretamente onde se deseja a sua ação
		ENTERAL, //ENTERAL efeito sistêmico (não-local); recebe-se a substância via trato digestivo
		PARENTERAL, //PARENTERAL  efeito sistêmico; recebe-se a substância por outra forma que não pelo trato digestivo
	}

	public enum Status {
		ACTIVE, INACTIVE;
	}

	public AdministrationRoute getAdministrationRoute() {
		return administrationRoute;
	}

	public void setAdministrationRoute(AdministrationRoute administrationRoute) {
		this.administrationRoute = administrationRoute;
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
		if (description != null && !description.trim().isEmpty())
			result += ", description: " + description;
		if (administrationRoute != null)
			result += ", administrationRoute: " + administrationRoute;
		if (status != null)
			result += ", status: " + status;
		return result;
	}

}