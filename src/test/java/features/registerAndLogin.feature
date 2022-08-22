Feature: Register and Login
  Scenario: Register to system and login
    # Open Url -> Hooks
    Given Open register page
    When Input to Email  "hoangtrang98123@gmail.com"
    And Click to Submit
    Then Get User and password info
    And Submit valid infor to login form
    Then Home page displayed
    When Back to Login page