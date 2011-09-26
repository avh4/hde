Feature: Implementing classes
  In order to implement object-oriented software
  As a software developer
  I want to implement classes

Scenario: Stack class
  Given a class "Stack":
    """
    public class Stack {
      public void push(Object o) {}
      public Object pop() {}
    }
    """
  And a class "StackTest":
    """
    """
  When I command "edit method: push"
  And I type:
    """
    array.add(o);
    """
  And I command "edit method: pop"
  And I type:
    """
    Object ret = array.get(array.size()-1);
    array.remove(array.size()-1);
    return ret;
    """
  
