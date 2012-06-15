Story: Writing code
  In order to implement a software program
  As a software developer
  I want to write code and have it run

Scenario: Java Hello World

Given a new project
When I command "nc", "HelloWorld"
And I command "nm", "main"
And I type
System.out.println("Hello, World");
Then the project should have no errors
And the project should have no warnings
And running the project should produce:
Hello, World

