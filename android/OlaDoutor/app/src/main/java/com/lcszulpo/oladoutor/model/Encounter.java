package com.lcszulpo.oladoutor.model;

import java.util.Date;

/**
 * Created by lcszulpo on 12/09/15.
 */
public class Encounter {

    private Integer id;
    private Date date;
    //Vital signals
    private Double pulseRate; //BPM
    private Double respiratoryRate; //BPM
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

    public enum Pain {
        NONE, MILD, MODERATE, SEVERE
    }

    public enum PainDetail {
        HEADACHE, SORE_THROAT, HEARTBURN, CHEST, BACK, MUSCLE_JOINT, ABDOMINAL_UPPER, ABDOMINAL_LOWER
    }

    public enum BleedingDetail {
        ORAL_BLEEDING, NOSEBLEED, VOMITING_BLOOD, VAGINAL_BLEEDING, COUGHING_UP_BLOOD,
        BLOOD_IN_URINE, BLEEDING_FROM_EYES, BLACK_BLOOD_IN_STOOL, RED_BLOOD_IN_STOOL
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

}
