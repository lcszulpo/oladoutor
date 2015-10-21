require 'test_helper'

class PatientControllerTest < ActionController::TestCase
  test "should get create" do
    get :create
    assert_response :success
  end

  test "should get update" do
    get :update
    assert_response :success
  end

  test "should get update_locale" do
    get :update_locale
    assert_response :success
  end

  test "should get show_actives_by_locale" do
    get :show_actives_by_locale
    assert_response :success
  end

end
