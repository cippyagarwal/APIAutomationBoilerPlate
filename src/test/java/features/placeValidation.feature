Feature: Validating Place API's
  @Addplace @regression
  Scenario Outline: Verify if Place is being succesfully added using AddplaceApi
    Given Add place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name  | language | address         |
      | Amit  | English  | 101, Main Cross |
      | cippy | Hindi    | 102, Jaipur     |


    @UpdatePlace @regression
    Scenario: Verify update place functionality
      Given update Place Payload
      When user calls "updateplaceAPI" with "PUT" http request
      Then the API call got success with status code 200
      And "msg" in response body is "Address successfully updated"
    @DeletePlace @regression
    Scenario: Verify Delete place functionality
      Given Delete place payload
      When user calls "deletePlaceAPI" with "POST" http request
      Then the API call got success with status code 200
      And "status" in response body is "OK"
