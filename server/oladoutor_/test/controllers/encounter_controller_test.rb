require 'test_helper'

class EncounterControllerTest < ActionController::TestCase
  test "should get create" do
    get :create
    assert_response :success
  end

  test "should get delete" do
    get :delete
    assert_response :success
  end

  test "should get show" do
    get :show
    assert_response :success
  end

  test "should get show_by_patient" do
    get :show_by_patient
    assert_response :success
  end

  test "should get find_last_by_patient" do
    get :find_last_by_patient
    assert_response :success
  end

end
