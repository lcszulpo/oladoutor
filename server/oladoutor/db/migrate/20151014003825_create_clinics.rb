class CreateClinics < ActiveRecord::Migration
  def change
    create_table :clinics do |t|
      t.string :description

      t.timestamps null: false
    end
  end
end
