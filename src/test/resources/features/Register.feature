Feature: Register on Basketballpage

  Scenario Outline: Successfull Registration
    Given The user uses "<browser>"
    And focus is on the first field date of birth
    And User has entered valid inputs
    When Accept all terms
    And User click on join button
    Then I get a membersnumber
    Examples:
    |browser|
    |chrome |
    |firefox|


  Scenario Outline: Unsuccessfull registration
    Given The user uses "<browser>"
    And focus is on the first field date of birth
    When User has entered invalid inputs "<caseName>"
    When Accept all terms
    And User click on join button
    Then User should get an "<errorMessage>"
  Examples:
     |browser |caseName | errorMessage |
     |chrome |invalidEmail | The Email Address field is not a valid e-mail address |
     |chrome |misMatchEmail | Confirm Email Address does not match                 |
     |chrome | missingPwd |  Password is required                                    |
     |chrome| misMatchPwd |  Password did not match                                 |
     |chrome  |pwdShort |   Password must be between 5 and 20 characters              |
     |chrome |pwdLong |   Password must be between 5 and 20 characters               |
     |chrome|missingFirstName |First Name is required                             |
     |chrome|missingLastName |Last Name is required                              |
     |firefox |invalidEmail | The Email Address field is not a valid e-mail address |
     |firefox |misMatchEmail | Confirm Email Address does not match                 |
     |firefox | missingPwd |  Password is required                                    |
     |firefox| misMatchPwd |  Password did not match                                 |
     |firefox  |pwdShort |   Password must be between 5 and 20 characters              |
     |firefox |pwdLong |   Password must be between 5 and 20 characters               |
     |firefox|missingFirstName |First Name is required                             |
     |firefox|missingLastName |Last Name is required                              |


  Scenario Outline: Not accepting all terms
    Given The user uses "<browser>"
    And focus is on the first field date of birth
    And User has entered valid inputs
    When Not accepting all "<terms>"
    And User click on join button
    Then User should get an "<errorMessage>"
  Examples:
      |browser|terms  | errorMessage |
      |chrome|understood       |You must confirm that you have read and accepted our Terms and Conditions      |
      |chrome|overEighteen       |You must confirm that you are over 18 or a person with parental responsibility      |
      |chrome|codeOfConduct       |You must confirm that you have read, understood and agree to the Code of Ethics and Conduct      |
      |firefox|understood       |You must confirm that you have read and accepted our Terms and Conditions      |
      |firefox|overEighteen       |You must confirm that you are over 18 or a person with parental responsibility      |
      |firefox|codeOfConduct       |You must confirm that you have read, understood and agree to the Code of Ethics and Conduct      |

    Scenario Outline: Check WCAG protocol
      Given The user uses "<browser>"
      And focus is on the first field date of birth
      When User has entered valid inputs by tabbing
      And Accepts all terms by using keyboard only
      And Navigates by tabbing and joins
      Then I get a membersnumber

      Examples:
        | browser|
        | chrome|
        | firefox|


