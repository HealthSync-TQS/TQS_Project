Feature: CheckIn


    Scenario: Search appointment by id and checkin
         When I navigate to "http://172.24.0.4:3001"
         And I search for appointment with id "2"
         And I click on the search button
         Then I should see "Patient: Jane" and "Nº Utente: 987654321"
         Then I click on the checkin button

    Scenario: Set appointment as paid and checkin
         When I navigate to "http://172.24.0.4:3001"
         And I search for appointment with patient "123456789"
         And I click on the search button
         Then I should see "Patient: John" and "Nº Utente: 123456789"
         And I click on the payment button
         Then I click on the confirm payment button





