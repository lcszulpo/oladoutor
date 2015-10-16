class PatientController < ApplicationController

  skip_before_filter :verify_authenticity_token

  def create
    if Patient.create(params[:name], params[:last_name], params[:age], params[:age_type], params[:sex], params[:locale_id])
      render :json => {msg: true}
    else
      render :json => {msg: false}
    end
  end

  def update
    if Patient.update(params[:id], params[:name], params[:last_name], params[:age], params[:age_type], params[:sex], params[:status], params[:locale_id])
      render :json => {msg: true}
    else
      render :json => {msg: false}
    end
  end

  def update_locale
    if Patient.update_locale(params[:id], params[:locale_id])
      render :json => {msg: true}
    else
      render :json => {msg: false}
    end
  end

  def show
    render :json => Patient.show
  end

  def show_actives_by_locale
    render :json => Patient.show_actives_by_locale(params[:locale_id])
  end
end
