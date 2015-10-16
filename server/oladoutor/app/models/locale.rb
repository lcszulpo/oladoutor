class Locale < ActiveRecord::Base

  has_many :patients

  def self.create(description)
    locale = Locale.new
    locale.description = description

    if locale.save
      true
    else
      false
    end
  end

  def self.update(id, description, status)
    locale = Locale.find(id)
    locale.description = description
    locale.status = status

    if locale.save
      true
    else
      false
    end
  end

  def self.show
    Locale.all
  end

end
