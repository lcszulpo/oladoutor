class DropColumnClinicIdFromLocale < ActiveRecord::Migration
  def change
    remove_column :locales, :clinic_id
  end
end
