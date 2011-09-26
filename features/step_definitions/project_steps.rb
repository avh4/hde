java_import 'net.avh4.ide.IDE'
java_import 'net.avh4.ide.Project'

Given /^a new project$/ do
  Given "I have never run IDE before"
  When "I launch IDE"
end

Given /^a project "([^"]*)"$/ do |name|
  Given "I have never run IDE before"
  When "I launch IDE"
end

Then /^there should be one project$/ do
  @ide.project.should_not be_nil
end

Then /^the current project should be "([^"]*)"$/ do |name|
  @ide.project.name.should == name
end
