class LocaleController < ApplicationController

  skip_before_filter :verify_authenticity_token

  def create
    if Locale.create(params[:description])
      render :json => {msg: true}
    else
      render :json => {msg: false}
    end
  end

  def update
    if Locale.update(params[:id], params[:description], params[:status])
      render :json => {msg: true}
    else
      render :json => {msg:false}
    end
  end

  def show
    render :json => Locale.show
  end

end
