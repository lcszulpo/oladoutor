class AddFieldToLocale < ActiveRecord::Migration
  def change
    add_column :locales, :clinic_id, :integer
  end
end
