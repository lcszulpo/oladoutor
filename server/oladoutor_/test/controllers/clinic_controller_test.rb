require 'test_helper'

class ClinicControllerTest < ActionController::TestCase
  test "should get save" do
    get :save
    assert_response :success
  end

  test "should get update" do
    get :update
    assert_response :success
  end

  test "should get list" do
    get :list
    assert_response :success
  end

  test "should get delete" do
    get :delete
    assert_response :success
  end

end
