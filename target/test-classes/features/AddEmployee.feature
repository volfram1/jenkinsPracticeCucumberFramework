Feature: Adding the employees in HRMS Application

  Background:
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user is successfully logged in
    When user clicks on PIM option
    And user clicks on add employee option

  @regression @safiur
  Scenario: Adding one employee from feature file
    #Given user is navigated to HRMS application
    And user enters firstname, middlename and lastname
    And user clicks on save button
    Then employee added successfully

    @123
    Scenario: Adding an employee using cucumber feature file
      And user enters "zuhoor" "Mujeeb" and "Silvia"
      And user clicks on save button
      Then employee added successfully


    Scenario Outline: Adding multiple employees
      And user provides "<firstName>" "<middleName>" and "<lastName>"
      And user clicks on save button
      Then employee added successfully
      Examples:
      |firstName|middleName|lastName|
      |asel     |MS        |tolga   |
      |yazgul   |MS        |kishan  |
      |tarik    |MS        |mujeeb  |
      |nassir   |MS        |volkan  |

  @test @datatable
  Scenario: Add employee using data table
    When user provides multiple employee data and verify they are added
      |firstName|middleName|lastName|
      |asel     |MS        |tolga   |
      |yazgul   |MS        |kishan  |
      |tarik    |MS        |mujeeb  |

    @excel
  Scenario: Addin multiple employees from excel file
    When user adds multiple employees from excel file using employee "EmployeeData" sheet and verify the user added

  @ETETest
  Scenario: Adding an employee using cucumber feature file
    And user enters "Basia" "Ania" and "Zosia"
    And user grabs the employee Id
    And user clicks on save button
    And user query the database for the same employee Id
    Then user verifies the results



