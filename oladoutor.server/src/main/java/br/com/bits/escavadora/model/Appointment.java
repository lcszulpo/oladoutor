package br.com.bits.escavadora.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import br.com.bits.escavadora.model.Medic;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import br.com.bits.escavadora.model.Patient;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "appointment")
public class Appointment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@ManyToOne(cascade = CascadeType.ALL)
	private Medic medic;

	@ManyToOne(cascade = CascadeType.ALL)
	private Patient patient;

	@Column(name = "date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name = "note")
	private String note;

	@Column(name = "reason")
	private String reason;

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
		if (!(obj instanceof Appointment)) {
			return false;
		}
		Appointment other = (Appointment) obj;
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

	public Medic getMedic() {
		return this.medic;
	}

	public void setMedic(final Medic medic) {
		this.medic = medic;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(final Patient patient) {
		this.patient = patient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (medic != null)
			result += ", medic: " + medic;
		if (patient != null)
			result += ", patient: " + patient;
		if (date != null)
			result += ", date: " + date;
		if (note != null && !note.trim().isEmpty())
			result += ", note: " + note;
		if (reason != null && !reason.trim().isEmpty())
			result += ", reason: " + reason;
		return result;
	}
}