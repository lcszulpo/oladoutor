class DropColumnClinicFromUser < ActiveRecord::Migration
  def change
    remove_column :users, :clinic_id
  end
end
