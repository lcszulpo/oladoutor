class AddFieldToPatient < ActiveRecord::Migration
  def change
    add_column :patients, :status, :boolean
  end
end
