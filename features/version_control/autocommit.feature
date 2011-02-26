Feature: Autocommit
  In order to improve my efficiency
  As a software developer
  I want to automate committing my code

  Scenario: After tests pass
    Given a project with passing tests
    When I add a new test that fails
    And I write code such that the new test passes (and all tests pass)
    Then my changes should automatically be committed
