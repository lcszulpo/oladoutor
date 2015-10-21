# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20151015230023) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "encounters", force: :cascade do |t|
    t.date     "date"
    t.decimal  "pulse_rate"
    t.decimal  "respiratory_rate"
    t.decimal  "temperature"
    t.decimal  "weight"
    t.integer  "vomit"
    t.integer  "diarrhea"
    t.string   "pain"
    t.string   "pain_detail"
    t.boolean  "has_bleeding"
    t.string   "bleeding_detail"
    t.boolean  "has_weakness"
    t.string   "weakness_detail"
    t.string   "other_symptoms"
    t.string   "consciousness"
    t.string   "mobility"
    t.string   "diet"
    t.string   "hydration"
    t.string   "condition"
    t.integer  "patient_id"
    t.datetime "created_at",       null: false
    t.datetime "updated_at",       null: false
  end

  create_table "locales", force: :cascade do |t|
    t.string   "description"
    t.boolean  "status"
    t.datetime "created_at",  null: false
    t.datetime "updated_at",  null: false
  end

  create_table "patients", force: :cascade do |t|
    t.string   "name"
    t.string   "last_name"
    t.integer  "age"
    t.string   "age_type"
    t.string   "sex",        limit: 1
    t.integer  "locale_id"
    t.datetime "created_at",           null: false
    t.datetime "updated_at",           null: false
    t.boolean  "status"
  end

  create_table "users", force: :cascade do |t|
    t.string   "name"
    t.string   "email"
    t.string   "password"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

end
