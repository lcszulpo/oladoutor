class ApplicationController < ActionController::Base
  include ::ActionController::Flash
  include ::ActionView::Layouts
  include ::ActionController::Helpers
  include ::ActionController::MimeResponds
  include ::ActionController::RequestForgeryProtection
  # Prevent CSRF attacks by raising an exception.
  # For APIs, you may want to use :null_session instead.
  # protect_from_forgery with: :exception
end
