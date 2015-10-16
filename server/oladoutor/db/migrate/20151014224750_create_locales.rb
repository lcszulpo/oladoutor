class CreateLocales < ActiveRecord::Migration
  def change
    create_table :locales do |t|
      t.string :description
      t.boolean :status

      t.timestamps null: false
    end
  end
end
