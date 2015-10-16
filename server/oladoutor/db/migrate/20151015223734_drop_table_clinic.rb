class DropTableClinic < ActiveRecord::Migration
  def change
    drop_table :clinics
  end
end
