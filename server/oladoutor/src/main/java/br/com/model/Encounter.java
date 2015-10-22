package br.com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import br.com.model.Patient;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;

@Entity
@Table(name = "encounter")
@XmlRootElement
public class Encounter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column(name = "date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name = "pulse_rate", nullable = false)
	private Double pulseRate;

	@Column(name = "respiratory_rate", nullable = false)
	private Double respiratoryRate;

	@Column(name = "temperature", nullable = false)
	private Double temperature;

	@Column(name = "weight", nullable = false)
	private Double weight;

	@Column(name = "vomit", nullable = false)
	private Integer vomit;

	@Column(name = "diarrhea", nullable = false)
	private Integer diarrhea;

	@Column(name = "pain", nullable = false)
	@Enumerated(EnumType.STRING)
	private Pain pain;

	@Column(name = "pain_detail", nullable = false)
	@Enumerated(EnumType.STRING)
	private PainDetail painDetail;

	@Column(name = "has_bleeding", nullable = false)
	private Boolean hasBleeding;

	@Column(name = "bleeding_detail", nullable = false)
	@Enumerated(EnumType.STRING)
	private BleedingDetail bleedingDetail;

	@Column(name = "has_weakness", nullable = false)
	private Boolean hasWeakness;

	@Column(name = "weakness_detail", nullable = false)
	@Enumerated(EnumType.STRING)
	private WeaknessDetail weaknessDetail;

	@Column(length = 70, name = "others_symptoms", nullable = false)
	private String othersSymptoms;

	@Column(name = "consciousness", nullable = false)
	@Enumerated(EnumType.STRING)
	private Consciousness consciousness;

	@Column(name = "mobility", nullable = false)
	@Enumerated(EnumType.STRING)
	private Mobility mobility;

	@Column(name = "diet", nullable = false)
	@Enumerated(EnumType.STRING)
	private Diet diet;

	@Column(name = "hydration", nullable = false)
	@Enumerated(EnumType.STRING)
	private Hydration hydration;

	@Column(name = "condition", nullable = false)
	@Enumerated(EnumType.STRING)
	private Condition condition;

	@ManyToOne(cascade = CascadeType.ALL)
	private Patient patient;

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
		if (!(obj instanceof Encounter)) {
			return false;
		}
		Encounter other = (Encounter) obj;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPulseRate() {
		return pulseRate;
	}

	public void setPulseRate(Double pulseRate) {
		this.pulseRate = pulseRate;
	}

	public Double getRespiratoryRate() {
		return respiratoryRate;
	}

	public void setRespiratoryRate(Double respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getVomit() {
		return vomit;
	}

	public void setVomit(Integer vomit) {
		this.vomit = vomit;
	}

	public Integer getDiarrhea() {
		return diarrhea;
	}

	public void setDiarrhea(Integer diarrhea) {
		this.diarrhea = diarrhea;
	}

	public Pain getPain() {
		return pain;
	}

	public void setPain(Pain pain) {
		this.pain = pain;
	}

	public PainDetail getPainDetail() {
		return painDetail;
	}

	public void setPainDetail(PainDetail painDetail) {
		this.painDetail = painDetail;
	}

	public Boolean getHasBleeding() {
		return hasBleeding;
	}

	public void setHasBleeding(Boolean hasBleeding) {
		this.hasBleeding = hasBleeding;
	}

	public BleedingDetail getBleedingDetail() {
		return bleedingDetail;
	}

	public void setBleedingDetail(BleedingDetail bleedingDetail) {
		this.bleedingDetail = bleedingDetail;
	}

	public Boolean getHasWeakness() {
		return hasWeakness;
	}

	public void setHasWeakness(Boolean hasWeakness) {
		this.hasWeakness = hasWeakness;
	}

	public WeaknessDetail getWeaknessDetail() {
		return weaknessDetail;
	}

	public void setWeaknessDetail(WeaknessDetail weaknessDetail) {
		this.weaknessDetail = weaknessDetail;
	}

	public String getOthersSymptoms() {
		return othersSymptoms;
	}

	public void setOthersSymptoms(String othersSymptoms) {
		this.othersSymptoms = othersSymptoms;
	}

	public Consciousness getConsciousness() {
		return consciousness;
	}

	public void setConsciousness(Consciousness consciousness) {
		this.consciousness = consciousness;
	}

	public Mobility getMobility() {
		return mobility;
	}

	public void setMobility(Mobility mobility) {
		this.mobility = mobility;
	}

	public Diet getDiet() {
		return diet;
	}

	public void setDiet(Diet diet) {
		this.diet = diet;
	}

	public Hydration getHydration() {
		return hydration;
	}

	public void setHydration(Hydration hydration) {
		this.hydration = hydration;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		if (date != null)
			result += ", date: " + date;
		if (pulseRate != null)
			result += ", pulseRate: " + pulseRate;
		if (respiratoryRate != null)
			result += ", respiratoryRate: " + respiratoryRate;
		if (temperature != null)
			result += ", temperature: " + temperature;
		if (weight != null)
			result += ", weight: " + weight;
		if (vomit != null)
			result += ", vomit: " + vomit;
		if (diarrhea != null)
			result += ", diarrhea: " + diarrhea;
		if (pain != null)
			result += ", pain: " + pain;
		return result;
	}

	public enum Pain {
		NONE, MILD, MODERATE, SEVERE
	}

	public enum PainDetail {
		HEADACHE, SORE_THROAT, HEARTBURN, CHEST, BACK, MUSCLE_JOINT, ABDOMINAL_UPPER, ABDOMINAL_LOWER
	}

	public enum BleedingDetail {
		ORAL_BLEEDING, NOSEBLEED, VOMITING_BLOOD, VAGINAL_BLEEDING, COUGHING_UP_BLOOD, BLOOD_IN_URINE, BLEEDING_FROM_EYES, BLACK_BLOOD_IN_STOOL, RED_BLOOD_IN_STOOL
	}

	public enum WeaknessDetail {
		NONE, MILD, MODERATE, SEVERE
	}

	public enum Consciousness {
		ALERT, RESPONDS_VOICE, RESPONDS_PAIN, DO_NOT_ANSWER
	}

	public enum Mobility {
		WALKS, WALKING_WITH_DIFFICULTY, ASSISTED, BED_BOUND
	}

	public enum Diet {
		EATING, ONLY_FLUIDS
	}

	public enum Hydration {
		ADEQUATE_FLUID_INTAKE, NEED_ORS, NEED_IV_FLUIDS
	}

	public enum Condition {
		WELL, UNWELL, CRITICAL, PALLIATIVE, CONVALESCING, SUSPECTED_DEATH, CONFIRMED_DEATH
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(final Patient patient) {
		this.patient = patient;
	}

}