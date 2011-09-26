Feature: Product backlog
  In order to keep development focused on delivering the most value
  As a product owner
  I want to create and maintain a product backlog

  Scenario: Creating a backlog
    Given a project "Calculator"
    And the product backlog is empty
    When I add an item to the product backlog "Adding numbers"
    And I add an item to the product backlog "Subtracting numbers"
    And I add an item to the product backlog "Negating a number"
    And I add an item to the product backlog "Calculating a percentage"
    And I estimate "Adding numbers" to be 2 story points
    And I estimate "Subtracting numbers" to be 1 story point
    And I estimate "Negating a number" to be 1 story point
    And I estimate "Calculating a percentage" to be 2 story points
    And I move "Adding numbers" to the top priority
    And I move "Calculating a percentage" to the second priority
    Then the product backlog should look like:
      """
      Adding numbers | 2
      Calculating a percentage | 2
      Subtracting numbers | 1
      Negating a number | 1
      """
  
