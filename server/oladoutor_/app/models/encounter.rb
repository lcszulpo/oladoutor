class Encounter < ActiveRecord::Base

  belongs_to :patient

  def self.create(date, pulse_rate, respiratory_rate, temperature, weight, vomit, diarrhea, pain, pain_detail, has_bleeding,bleeding_detail, has_weakness, weakness_detail, other_symptoms, consciousness, mobility, diet, hydration, condition, patient_id)
    encounter = Encounter.new

    encounter.date = date
    encounter.pulse_rate = pulse_rate
    encounter.respiratory_rate = respiratory_rate
    encounter.temperature = temperature
    encounter.weight = weight
    encounter.vomit = vomit
    encounter.diarrhea = diarrhea
    encounter.pain = pain
    encounter.pain_detail = pain_detail
    encounter.has_bleeding = has_bleeding
    encounter.bleeding_detail = bleeding_detail
    encounter.has_weakness = has_weakness
    encounter.weakness_detail = weakness_detail
    encounter.other_symptoms = other_symptoms
    encounter.consciousness = consciousness
    encounter.mobility = mobility
    encounter.diet = diet
    encounter.hydration = hydration
    encounter.condition = condition
    encounter.patient_id = patient_id

    if(encounter.save)
      true
    else
      false
    end
  end

  def self.delete(encounter_id)
    encounter = Encounter.find(encounter_id)
    if encounter.destroy
      true
    else
      false
    end
  end

  def self.show
    Encounter.all
  end

  def self.show_by_patient(patient_id)
    Encounter.where(patient_id: patient_id).order(:date)
  end

  def self.find_last_by_patient(patient_id)
    Encounter.where(patient_id: patient_id).last
  end

end
