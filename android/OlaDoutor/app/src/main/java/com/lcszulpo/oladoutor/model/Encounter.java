package com.lcszulpo.oladoutor.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lcszulpo on 12/09/15.
 */
public class Encounter implements Serializable {

    private Integer id;
    private Date date;
    //Vital signals
    private Integer pulseRate; //BPM
    private Integer respiratoryRate; //BPM
    private Double temperature; //ºC
    private Double weight; //Kg
    //Symptoms
    private Integer vomit; //In last 24 hours
    private Integer diarrhea; //In last 24 hours
    private Pain pain;
    private PainDetail painDetail;
    private Boolean hasBleeding;
    private BleedingDetail bleedingDetail;
    private Boolean hasWeakness;
    private WeaknessDetail weaknessDetail;
    private String othersSymptoms;
    //State
    private Consciousness consciousness;
    private Mobility mobility;
    private Diet diet;
    private Hydration hydration;
    private Condition condition;

    private Patient patient;

    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPulseRate() {
        return pulseRate;
    }

    public void setPulseRate(Integer pulseRate) {
        this.pulseRate = pulseRate;
    }

    public Integer getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(Integer respiratoryRate) {
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        return format.format(getDate());
    }

    public enum Pain {
        NONE("Nenhuma"),
        MILD("Suave"),
        MODERATE("Moderada"),
        SEVERE("Severa");

        private String description;

        private Pain(String description) {
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

    public enum PainDetail {
        NONE("Nenhuma"),
        HEADACHE("Dor de cabeça"),
        SORE_THROAT("Dor de garganta"),
        HEARTBURN("Azia"),
        CHEST("Dor no peito"),
        BACK("Dor nas costas"),
        MUSCLE_JOINT("Dor nas articulações"),
        ABDOMINAL_UPPER("Dor abdominal superior"),
        ABDOMINAL_LOWER("Dor abdominal inferior");

        private String description;

        private PainDetail(String description) {
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

    public enum BleedingDetail {
        NONE("Nenhuma"),
        ORAL_BLEEDING("Hemorragia oral"),
        NOSEBLEED("Hemorragia nasal"),
        VOMITING_BLOOD("Vomitando sangue"),
        COUGHING_UP_BLOOD("Tossindo sangue"),
        BLOOD_IN_URINE("Sangue na urína"),
        BLEEDING_FROM_EYES("Sangramento pelos olhos");

        private String description;

        private BleedingDetail(String description) {
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

    public enum WeaknessDetail {
        NONE("Nenhuma"),
        MILD("Suave"),
        MODERATE("Moderada"),
        SEVERE("Severa");

        private String description;

        private WeaknessDetail(String description) {
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

    public enum Consciousness {
        ALERT("Alerta"),
        RESPONDS_VOICE("Responde vozes"),
        RESPONDS_PAIN("Responde dores"),
        DO_NOT_ANSWER("Não está respondendo");

        private String description;

        private Consciousness(String description) {
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

    public enum Mobility {
        WALKS("Caminhando"),
        WALKING_WITH_DIFFICULTY("Caminhando com dificuldade"),
        ASSISTED("Caminhando com ajuda"),
        BED_BOUND("Permanece na cama");

        private String description;

        private Mobility(String description) {
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

    public enum Diet {
        EATING("Comendo"),
        ONLY_FLUIDS("Apenas fluídos");

        private String description;

        private Diet(String description) {
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

    public enum Hydration {
        ADEQUATE_FLUID_INTAKE("Adequada"),
        NEED_ORS("Precisa de hidratação oral"),
        NEED_IV_FLUIDS("Precisa de fluídos via intravenosa");

        private String description;

        private Hydration(String description) {
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

    public enum Condition {
        WELL("Ok"),
        UNWELL("indisposto"),
        CRITICAL("Crítico"),
        PALLIATIVE("Melhora momentânea"),
        CONVALESCING("Se curando"),
        SUSPECTED_DEATH("Suspeita de morte"),
        CONFIRMED_DEATH("Morte confirmada");

        private String description;

        private Condition(String description) {
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

}
