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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class GetTicketsSteps {

    WebDriver ticketDriver;
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


    @And("I click on the next check-in ticket and insert the desk number 10")
    public void clickNextCheckInTicket() {
        WebElement nextTicketButton = ticketDriver.findElement(By.id("CallNextCheckInTicket"));
        Actions actions = new Actions(ticketDriver);
        actions.moveToElement(nextTicketButton).click().perform();

        WebDriverWait wait = new WebDriverWait(ticketDriver, Duration.ofSeconds(60)); // Aguarde até 10 segundos
        WebElement checkinput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deskNumber")));
        checkinput.sendKeys("10");

        WebElement callButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("NextCheckin")));
        Actions actions1 = new Actions(ticketDriver);
        actions1.moveToElement(callButton).click().perform();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("deskNumber")));

    }



    @Then("the check-in queue should have -1 tickets remaining")
    public void checkCheckInQueueSize() {

        WebElement queueElement = ticketDriver.findElement(By.id("checkinQueue"));
        String queueText = queueElement.getText();
        int newQueueSize = Integer.parseInt(queueText.split(" ")[0]);
        System.out.println("Queue size:" + CheckInQueueSize);
        System.out.println("Queue size atual: " + newQueueSize);
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

        assertNotEquals(0, AppointmentQueueSize);
    }

    @And("I click on the next appointment ticket and insert the desk number 10")
    public void clickNextAppointmentTicket() {
        WebElement nextAppointmentButton = ticketDriver.findElement(By.id("CallNextAppointmentTicket"));
        Actions actions = new Actions(ticketDriver);
        actions.moveToElement(nextAppointmentButton).click().perform();

        WebDriverWait wait = new WebDriverWait(ticketDriver, Duration.ofSeconds(60)); // Aguarde até 10 segundos
        WebElement appointmentInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("clinicInput")));
        appointmentInput.sendKeys("10");

        WebElement callButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("nextAppointment")));
        Actions actions1 = new Actions(ticketDriver);
        actions1.moveToElement(callButton).click().perform();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("clinicInput")));

    }

    @Then("the appointment queue should have -1 tickets remaining")
    public void checkAppointmentQueueSize() {
        WebElement queueElement = ticketDriver.findElement(By.id("appointmentQueue"));
        String queueText = queueElement.getText();
        int newQueueSize = Integer.parseInt(queueText.split(" ")[0]);
        System.out.println("Queue size:" + AppointmentQueueSize);
        System.out.println("Queue size atual: " + newQueueSize);
        assertEquals(AppointmentQueueSize - 1, newQueueSize);
        ticketDriver.quit();
    }





}
