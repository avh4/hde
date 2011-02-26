require 'java'
$CLASSPATH << "bin/"
java_import 'net.avh4.ide.IDE'

Given /^a new project$/ do
  @ide = IDE.new
end

When /^I command "([^"]*)", "([^"]*)"$/ do |command, text|
  @ide.performUserCommand(command, text)
end

When /^I type$/ do |text|
  @ide.enterText(text)
end

Then /^the project should have no errors$/ do
  @ide.getErrors.should == []
end

Then /^the project should have no warnings$/ do
  @ide.getWarnings.should == []
end

Then /^running the project should produce:$/ do |output|
  results = @ide.runProject
  results.stdout.should == output
end
