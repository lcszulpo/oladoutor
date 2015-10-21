class UserController < ApplicationController

  skip_before_filter :verify_authenticity_token

  def create
    if User.create(params[:name], params[:email], params[:password])
      render :json => {msg: true}
    else
      render :json => {msg: false}
    end
  end

  def update
    if User.update(params[:id], params[:name], params[:email], params[:password])
      render :json => {msg: true}
    else
      render :json => {msg: false}
    end
  end

  def login
    if User.find_by_email_password(params[:email], params[:password])
      render :json => {msg:true}
    else
      render :json => {msg:false}
    end
  end

end
