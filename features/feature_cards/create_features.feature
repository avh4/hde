Feature: Create features
  In order to visualize and communicate the product feature set and product backlog
  As a product developer
  I want to create feature cards and store them in a standard format

Scenario: Basic feature cards
  Given a new project
  When I create a feature "Choosing a list"
  And I create a feature "Creating a custom list"
  And I create a feature "Morning reminder"
  And I create a feature "Entering notes"
  Then there should be files:
    """
    features/choosing_a_list.feature
    features/creating_a_custom_list.feature
    features/morning_reminder.feature
    features/entering_notes.feature
    """



  
