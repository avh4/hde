require 'digest/sha1'

Given /^the product backlog is empty$/ do
  @backlog = @ide.project.getProductBacklog
  
  @backlog.getNumberOfFeatures.should == 0
end

When /^I add an item to the product backlog "([^"]*)"$/ do |name|
  @ui.type name
  @ui.pressEnter
end

When /^I estimate "([^"]*)" to be (\d+) story points?$/ do |name, estimate|
  index = @backlog.findFeatureByName(name)
  @ui.clickOn "story points", index
  @ui.type estimate
  @ui.pressEnter
  
  @backlog.getFeatureEstimate(index).should == estimate.to_i
end

When /^I move "([^"]*)" to the top priority$/ do |name|
  index = @backlog.findFeatureByName(name)
  @ui.dragAndDrop "feature", index, "features", 0
  
  @backlog.getFeatureName(0).should == name
end

When /^I move "([^"]*)" to the second priority$/ do |name|
  index = @backlog.findFeatureByName(name)
  @ui.dragAndDrop "feature", index, "features", 1

  @backlog.getFeatureName(1).should == name
end

Then /^the product backlog should look like:$/ do |string|
  hash = Digest::SHA1.hexdigest string
  @ide.getProductBacklogView.should look_like "#{hash}.png"
end
