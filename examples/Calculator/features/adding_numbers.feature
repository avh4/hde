Feature: Adding numbers

  Scenario: 53+45+16+104
    Given a calculator
    When I add 53, 45, 16, 104
    Then the result should be 218
  
