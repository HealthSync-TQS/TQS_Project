Feature: ScheduleAppointment

  Scenario: Schedule appointment
        When I navigate to "http://localhost:3003"
        And I insert as Patient Number "10001"
        And I insert as Patient Name "John Doe"
        And I insert as Email "john.doe@example.com"
        And I select as HealthCare Unit "Centro de Saúde Delta"
        And I select as HealthCare Unit "Cardiology"
        
        And I click on the search times button 
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