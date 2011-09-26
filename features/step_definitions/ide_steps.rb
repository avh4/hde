require 'sandbox'

java_import 'net.avh4.ide.IDE'
java_import 'net.avh4.ide.IDETester'
java_import 'net.avh4.ide.Project'
java_import 'net.avh4.system.datastore.FileDataStore'

Around do |scenario, block|
  Sandbox.play do |path|
    Dir.chdir path
    block.call
  end
end

Given /^I have never run IDE before$/ do
  # Make sure that none of the configuration data exists
  Dir.entries(".").should == [".", ".."]
end

When /^I launch IDE$/ do
  datastore = FileDataStore.new(Dir.pwd)
  @ide = IDE.new datastore
  @ui = IDETester.new @ide
end

When /^I command "([^"]*)", "([^"]*)"$/ do |command, text|
  fail "No UI design"
  @ide.userCommand(command, text)
end

When /^I type$/ do |text|
  @ui.type text
end

Then /^the project should have no errors$/ do
  @ide.errors.size.should == 0
end

Then /^the project should have no warnings$/ do
  @ide.warnings.size.should == 0
end

Then /^running the project should produce:$/ do |output|
  fail "No UI design"
  results = @ide.runProject
  results.stdout.should == output
end

Then /^there should be files:$/ do |string|
  files = string.split
  string.split.each do |filename|
    fail "Expected #{filename} to exist" unless File.exists?(filename)
  end
end
