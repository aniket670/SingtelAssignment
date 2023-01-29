Feature: To test the TodoMVC functionality
    #Author :Aniket Khandagale
    #Created Date : 29th Jan, 2023
  @test
  Scenario Outline: To verify the user is able to add the ToDo record
    Given the user is on the application
    And the user input the todo as <record>
    Then the user verify the <record> is added successfully to <tab>
    And the user verify the <record> in <tab1>
    Examples:
      | record  | tab | tab1   |
      | test123 | All | Active |

  @test
  Scenario Outline: To verify the user is able to delete the added record
    Given the user is on the application
    And the user input the todo as <record>
    And the user input the todo as <record2>
    When the user tries to delete the <record>
    Then the user verify deleted <record> not displayed in <tab>
    Examples:
      | record  | record2 | tab |
      | test123 | XYZ     | All |

  @test
  Scenario Outline: To verify the user is able to mark done
    Given the user is on the application
    And the user input the todo as <record>
    When the user selects the <record> as done
    Then the user verify the clearCompleted button is visible
    And the user verify the <record> in <tab1>
    Examples:
      | record  | tab1      |
      | test123 | Completed |

  @test
  Scenario Outline: To verify the clear completed button functionality
    Given the user is on the application
    And the user input the todo as <record1>
    And the user input the todo as <record2>
    And the user selects the <record1> as done
    When the user clicks on clearCompleted
    Then the user verify deleted <record> not displayed in <tab>
    Examples:
      | record1 | record2 | tab       |
      | test123 | XYZ     | Completed |

  @test
  Scenario Outline: To verify the unchecking of done record
    Given the user is on the application
    And the user input the todo as <record1>
    And the user input the todo as <record2>
    And the user selects the <record1> as done
    And the user verify the clearCompleted button is visible
    And the user verify the <record1> in <tab>
    And the user verify deleted <record> not displayed in <tab1>
    When the user selects the <record1> as done
    Then the user verify the <record1> in <tab1>
    And the user verify deleted <record> not displayed in <tab>
    And the user verify the clearCompleted button is hidden
    Examples:
      | record1 | record2 | tab       | tab1   |
      | test123 | XYZ     | Completed | Active |
