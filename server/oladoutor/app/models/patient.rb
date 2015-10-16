class Patient < ActiveRecord::Base

  belongs_to :locale
  has_many :encounters

  def self.create(name, last_name, age, age_type, sex, locale_id)
    patient = Patient.new
    patient.name = name
    patient.last_name = last_name
    patient.age = age
    patient.age_type = age_type
    patient.sex = sex
    patient.status = true
    patient.locale_id = locale_id

    if(patient.save)
      true
    else
      false
    end
  end

  def self.update(id, name, last_name, age, age_type, sex, status, locale_id)
    patient = Patient.find(id)
    patient.name = name
    patient.last_name = last_name
    patient.age = age
    patient.age_type = age_type
    patient.sex = sex
    patient.status = status
    patient.locale_id = locale_id

    if(patient.save)
      true
    else
      false
    end
  end

  def self.update_locale(id, locale_id)
    patient = Patient.find(id)
    patient.locale_id = locale_id

    if(patient.save)
      true
    else
      false
    end
  end

  def self.show
    Patient.all
  end

  def self.show_actives_by_locale(locale_id)
    Patient.where(status: true).where(locale_id: locale_id)
  end

end
