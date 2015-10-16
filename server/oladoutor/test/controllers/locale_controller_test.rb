require 'test_helper'

class LocaleControllerTest < ActionController::TestCase
  test "should get create" do
    get :create
    assert_response :success
  end

  test "should get update" do
    get :update
    assert_response :success
  end

  test "should get show" do
    get :show
    assert_response :success
  end

  test "should get show_by_clinic" do
    get :show_by_clinic
    assert_response :success
  end

end
