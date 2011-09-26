Feature: Calculating percentages

  Scenario: 10 in 160
    Given a calculator
    When I calculate a percentage 10 in 160
    Then the result should be 14%
  
