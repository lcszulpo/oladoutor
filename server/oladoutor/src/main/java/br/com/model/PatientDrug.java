package br.com.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import br.com.model.Patient;
import br.com.model.Drug;
import br.com.model.PatientDrug.IntervalType;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@XmlRootElement
public class PatientDrug implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@ManyToOne(optional = false)
	private Patient patient;

	@ManyToOne(optional = false)
	private Drug drug;

	@Column(name = "interval", nullable = false)
	private Integer interval;

	@Column(name = "interval_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private IntervalType intervalType;

	@Column(name = "time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PatientDrug)) {
			return false;
		}
		PatientDrug other = (PatientDrug) obj;
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
		MINUTES, HOURS;
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

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (patient != null)
			result += ", patient: " + patient;
		if (drug != null)
			result += ", drug: " + drug;
		if (interval != null)
			result += ", interval: " + interval;
		if (intervalType != null)
			result += ", intervalType: " + intervalType;
		if (time != null)
			result += ", time: " + time;
		return result;
	}
}