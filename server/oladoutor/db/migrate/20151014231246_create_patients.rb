class CreatePatients < ActiveRecord::Migration
  def change
    create_table :patients do |t|
      t.string :name
      t.string :last_name
      t.integer :age
      t.string :age_type
      t.string :sex, :limit => 1
      t.integer :locale_id

      t.timestamps null: false
    end
  end
end
