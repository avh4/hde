Feature: Projects
  In order to organize different projects
  As a developer
  I want to create and navigate between projects

Scenario: Initial project
  Given I have never run IDE before
  When I launch IDE
  Then there should be one project
  And the current project should be "New Project"
