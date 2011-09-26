Feature: Defining classes
  In order to implement object-oriented software
  As a software developer
  I want to define classes

  Scenario: Stack
    Given a new project
    When I command "new class: Stack"
    And I command "new method: push"
    And I command "new method: pop"
    Then there should be a class "Stack"
    And class "Stack" should have 2 methods
    And class "Stack" should have a method "push"
    And class "Stack" should have a method "pop"
    And the diagram of class "Stack" should look like "Stack.png"
  
