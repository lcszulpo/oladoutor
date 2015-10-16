class EncounterController < ApplicationController

  skip_before_filter :verify_authenticity_token

  def create
    if Encounter.create(params[:date], params[:pulse_rate], params[:respiratory_rate], params[:temperature], params[:weight], params[:vomit], params[:diarrhea], params[:pain], params[:pain_detail], params[:has_bleeding], params[:bleeding_detail], params[:has_weakness], params[:weakness_detail], params[:other_symptoms], params[:consciousness], params[:mobility], params[:diet], params[:hydration], params[:condition], params[:patient_id])
      render :json => {msg: true}
    else
      render :json => {msg: false}
    end
  end

  def delete
    if Encounter.delete(params[:encounter_id])
      render :json => {msg: true}
    else
      render :json => {msg: false}
    end
  end

  def show
    render :json => Encounter.show
  end

  def show_by_patient
    render :json => Encounter.show_by_patient(params[:patient_id])
  end

  def find_last_by_patient
    render :json => Encounter.find_last_by_patient(params[:patient_id])
  end
end
