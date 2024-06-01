package project.backend.functional.tickets;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class GetTicketsSteps {

    private WebDriver ticketDriver;
    private final String BASE_URL = "http://localhost:8080"; // Ajuste a URL conforme necessário
    private ResponseEntity<String> response;

    int CheckInQueueSize = 0;
    int AppointmentQueueSize = 0;


    @When("I navigate to staff portal at {string}")
    public void navigateToAppointmentManagementSystem(String url) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");


        ticketDriver = new ChromeDriver(options);
        ticketDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ticketDriver.get(url);
    }


    @And("the check-in queue is not empty")
    public void checkInQueueNotEmpty() {


        WebElement queueElement = ticketDriver.findElement(By.id("checkinQueue"));
        String queueText = queueElement.getText();
        CheckInQueueSize = Integer.parseInt(queueText.split(" ")[0]);

        if (CheckInQueueSize == 0) {
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.postForEntity(BASE_URL + "/newTicket", null, String.class);
            System.out.println("Ticket created: " + response.getBody());
            CheckInQueueSize ++;
            WebDriverWait wait = new WebDriverWait(ticketDriver, Duration.ofSeconds(5));



        }

        System.out.println("Queue size: " + CheckInQueueSize);
        assertNotEquals(0, CheckInQueueSize);
    }


    @And("I click on the next check-in ticket")
    public void clickNextCheckInTicket() {
        WebElement nextTicketButton = ticketDriver.findElement(By.id("CallNextCheckInTicket"));
        Actions actions = new Actions(ticketDriver);
        actions.moveToElement(nextTicketButton).click().perform();
    }


    @And("I insert the desk number {string}")
    public void insertDeskNumber(String deskNumber) {
        WebElement deskNumberInput = ticketDriver.findElement(By.id("deskNumber"));
        deskNumberInput.sendKeys(deskNumber);
        WebElement callButton = ticketDriver.findElement(By.id("NextCheckin"));
        callButton.click();

    }


    @Then("the check-in queue should have -1 tickets remaining")
    public void checkCheckInQueueSize() {

        WebElement queueElement = ticketDriver.findElement(By.id("checkinQueue"));
        String queueText = queueElement.getText();
        int newQueueSize = Integer.parseInt(queueText.split(" ")[0]);
        System.out.println("New queue size: " + newQueueSize);
        assertEquals(CheckInQueueSize - 1, newQueueSize);
    }

    @And("the appointment queue is not empty")
    public void appointmentQueueNotEmpty() {


        WebElement queueElement = ticketDriver.findElement(By.id("appointmentQueue"));
        String queueText = queueElement.getText();
        AppointmentQueueSize = Integer.parseInt(queueText.split(" ")[0]);

        if(AppointmentQueueSize == 0) {
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.postForEntity(BASE_URL + "/newAppointmentTicket?specialty=cardiology", null, String.class);
            System.out.println("Appointment created: " + response.getBody());
            AppointmentQueueSize++;
        }

        System.out.println("Queue size: " + AppointmentQueueSize);
        assertNotEquals(0, AppointmentQueueSize);
    }

    @And("I click on the next appointment ticket")
    public void clickNextAppointmentTicket() {
        WebElement nextTicketButton = ticketDriver.findElement(By.id("CallNextAppointmentTicket"));
        Actions actions = new Actions(ticketDriver);
        actions.moveToElement(nextTicketButton).click().perform();
    }

    @And("I insert the clinic number {string}")
    public void insertClinicNumber(String clinicNumber) {
        WebElement clinicInput = ticketDriver.findElement(By.id("clinicInput"));
        clinicInput.sendKeys(clinicNumber);
        WebElement callButton = ticketDriver.findElement(By.id("nextAppointment"));
        callButton.click();
    }

    @Then("the appointment queue should have -1 tickets remaining")
    public void checkAppointmentQueueSize() {
        WebElement queueElement = ticketDriver.findElement(By.id("appointmentQueue"));
        String queueText = queueElement.getText();
        int newQueueSize = Integer.parseInt(queueText.split(" ")[0]);
        assertEquals(CheckInQueueSize - 1, newQueueSize);
        ticketDriver.quit();
    }





}
