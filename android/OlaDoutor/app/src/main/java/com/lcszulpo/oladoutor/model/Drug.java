package com.lcszulpo.oladoutor.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public class Drug implements Serializable {

	private Long id;
	private int version;
	private String description;
	private AdministrationRoute administrationRoute;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public enum AdministrationRoute {
		TOPICAL("Tópica"), //TÓPICA  efeito local; a substância é aplicada diretamente onde se deseja a sua ação
		ENTERAL("Enteral"), //ENTERAL efeito sistêmico (não-local); recebe-se a substância via trato digestivo
		PARENTERAL("Parenteral"); //PARENTERAL  efeito sistêmico; recebe-se a substância por outra forma que não pelo trato digestivo

		private String description;

		private AdministrationRoute(String description) {
			this.description = description;
		}

		@Override
		public String toString() {
			return description;
		}

		@JsonValue
		public String toValue() {
			return this.name();
		}
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
		return description;
	}
}