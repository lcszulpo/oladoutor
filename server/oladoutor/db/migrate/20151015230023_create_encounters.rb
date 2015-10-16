class CreateEncounters < ActiveRecord::Migration
  def change
    create_table :encounters do |t|
      t.date :date
      t.decimal :pulse_rate
      t.decimal :respiratory_rate
      t.decimal :temperature
      t.decimal :weight
      t.integer :vomit
      t.integer :diarrhea
      t.string :pain
      t.string :pain_detail
      t.boolean :has_bleeding
      t.string :bleeding_detail
      t.boolean :has_weakness
      t.string :weakness_detail
      t.string :other_symptoms
      t.string :consciousness
      t.string :mobility
      t.string :diet
      t.string :hydration
      t.string :condition
      t.integer :patient_id

      t.timestamps null: false
    end
  end
end
