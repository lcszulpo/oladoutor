package com.lcszulpo.oladoutor.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Date;

public class PatientDrug {

	private Long id;
	private int version;
	private Patient patient;
	private Drug drug;
	private Integer interval;
	private IntervalType intervalType;
	private Date time;

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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public enum IntervalType {
		MINUTES("Minutos"),
		HOURS("Horas");

		private String description;

		private IntervalType(String description) {
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

	public IntervalType getIntervalType() {
		return intervalType;
	}

	public void setIntervalType(IntervalType intervalType) {
		this.intervalType = intervalType;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}