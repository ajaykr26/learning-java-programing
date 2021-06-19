Feature: API Testing Practice

  Scenario Outline: Send a create product request to the server and validate the response
    Given I send a login request to the server
    When I send an insert product request to the server "" "" "" ""
    Then I should receive a status code of 201 created status code
    Examples:
      | productId | name      | description | price |
      | 123       | Product 1 | Describe me | 500   |

  Scenario: Send a list products request to the server and validate the response
    Given I send a login request to the server
    When I send a get product list request from the server
    Then I should receive the list of products correctly
    And the products id should match expected result