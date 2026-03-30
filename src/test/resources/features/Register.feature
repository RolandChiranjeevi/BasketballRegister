Feature: Register on Basketballpage

  Background:
    Given User is on registrationpage





  Scenario Outline: Successfull Registration

    And focus is on the first field date of birth
    And User has entered valid inputs
    When Accept all terms
    And User click on join button
    Then I get a membersnumber
    Examples:


  Scenario Outline: Unsuccessfull registration

    And focus is on the first field date of birth
    When User has entered invalid inputs "<caseName>"
    When Accept all terms
    And User click on join button
    Then User should get an "<errorMessage>"
  Examples:
      |caseName | errorMessage |
     | invalidEmail | The Email Address field is not a valid e-mail address |
      |misMatchEmail | Confirm Email Address does not match                 |
      | missingPwd |  Password is required                                    |
      | misMatchPwd |  Password did not match                                 |
      |pwdShort |   Password must be between 5 and 20 characters              |
      |pwdLong |   Password must be between 5 and 20 characters               |
      |missingFirstName |First Name is required                             |
      |missingLastName |Last Name is required                              |


  Scenario Outline: Not accepting all terms
    And focus is on the first field date of birth
    And User has entered valid inputs
    When Not accepting all "<terms>"
    And User click on join button
    Then User should get an "<errorMessage>"
  Examples:
      |terms  | errorMessage |
      |understood       |You must confirm that you have read and accepted our Terms and Conditions      |
      |overEighteen       |You must confirm that you are over 18 or a person with parental responsibility      |
      |codeOfConduct       |You must confirm that you have read, understood and agree to the Code of Ethics and Conduct      |

    Scenario: Check WCAG protocol


