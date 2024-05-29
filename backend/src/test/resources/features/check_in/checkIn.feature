Feature: CheckIn

  Scenario: Search appointment by id
        When I navigate to "http://172.24.0.4:3001"
        And I search for appointment with id "2"
        And I click on the search button
        Then I should see "Patient: Jane" and "Nº Utente: 987654321"

    Scenario: Search appointment by patient
        When I navigate to "http://localhost:3001"
        And I search for appointment with patient "123456789"
        And I click on the search button
        Then I should see "Patient: John" and "Nº Utente: 123456789"


    Scenario: Set appointment as paid and checkin
            When I navigate to "http://localhost:3001"
            And I search for appointment with id "1"
            And I click on the search button
            Then I should see "Patient: John" and "Nº Utente: 123456789"
            And I click on the payment button
            And I click on the confirm payment button
            And I click on the checkin button
            Then I click on the get ticket button




