Feature: User stories
  In order to detail the features of a system
  As a developer
  I want to enter user stories to prompt conversations about new features

  Scenario: Entering user stories
    Given a project "Calculator"
    And the product backlog is:
      """
      Adding numbers | 2
      Calculating a percentage | 2
      Subtracting numbers | 2
      Negating a number | 2
      """
    When event
    Then outcome
  
