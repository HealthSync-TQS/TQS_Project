Feature: Schedule Appointment

    Scenario: See available dates to consult
        When I go to "http://172.24.0.6:3003"
        And I insert Patient Number "1001"
        And I insert Patient Name "John Doe"
        And I insert Email "john.doe@example.com"
        And I select HealthCare Unit "Centro de Saúde Delta"
        And I select Specialty "Cardiology"
        And I click on the search times button
        Then I should go to "http://172.24.0.6:3003/schedule"
        When I choose date "6 June"
        And I choose slot "22:20:00"
        And I click on the schedule appointment button
        Then I should go to home "http://172.24.0.6:3003/"
