Story: Features

Scenario: Adding a story without steps

Scenario: Adding a story with steps

Scenario: Immediately executing stories

Given a new project
When I create a new story named "Features"
And I create a new scenario in "Features" named "Adding a story without steps"
And I create a new scenario in "Features" named "Immediately executing stories"
And I run the integration tests
Then I should see test results
  Features
    > Adding a story without steps [PENDING]
    > Immediately executing stories [PENDING]
And the project should include
  src/features/stories/features.story
And the file src/features/stories/features.story should read:
  Story: Features
  
  Scenario: "Adding a story without steps"
  
  Scenario: "Immediately executing stories"

Scenario: Making stories pass