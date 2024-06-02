Feature: Get Tickets

    Scenario: Call next checkin ticket
        When I navigate to staff portal at "http://172.24.0.4:3001"
        And the check-in queue is not empty
        And I click on the next check-in ticket and insert the desk number 10
        Then the check-in queue should have -1 tickets remaining
        And the appointment queue is not empty
        And I click on the next appointment ticket and insert the desk number 10
        Then the appointment queue should have -1 tickets remaining

