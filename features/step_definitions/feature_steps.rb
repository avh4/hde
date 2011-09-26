
When /^I create a feature "([^"]*)"$/ do |name|
  @ui.clickOn "product backlog"
  @ui.type name
  @ui.pressEnter
end
