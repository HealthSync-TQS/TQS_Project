package project.backend.functional.check_in;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckInSteps {

    private WebDriver driver;





    @When("I navigate to {string}")
    public void i_access(String string) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(string);


    }

    @When("I search for appointment with id {string}")
    public void i_search_for_appointment_with_id(String id) {
        WebElement inputElement = driver.findElement(By.cssSelector("#inputId"));
        inputElement.sendKeys(id);
    }

    @When("I search for appointment with patient {string}")
    public void i_search_for_appointment_with_patient(String utente) {
        WebElement inputElement = driver.findElement(By.cssSelector("#inputUtente"));
        inputElement.sendKeys(utente);
    }

    @When("I click on the search button")
    public void i_click_on_the_search_button() {
        WebElement searchButton = driver.findElement(By.xpath("//button[text()='Search']"));
        searchButton.click();


    }

    @Then("I should see {string} and {string}")
    public void i_should_see_name_and(String name, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement patientNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PatientName")));
        WebElement patientNumberElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PatientUtente")));

        System.out.println(patientNameElement.getText());
        System.out.println(patientNumberElement.getText());
        assert patientNameElement.getText().contains(name);
        assert patientNumberElement.getText().contains(text);
    }


    //            And I click on the payment button
    @When("I click on the payment button")
    public void i_click_on_the_payment_button() {
        WebElement paymentButton = driver.findElement(By.id("payButton"));
        paymentButton.click();
    }

    //            And I click on the confirm payment button
    @When("I click on the confirm payment button")
    public void i_click_on_the_confirm_payment_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement confirmPaymentButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPaymentButton")));
        confirmPaymentButton.click();

    }

    @Then("I should see {string} button")
    public void i_should_see_button(String text) {
        WebElement checkinButton = driver.findElement(By.id("paid"));
        assertTrue(checkinButton.getText().contains(text));
    }

//             And I click on the checkin button
    @When("I click on the checkin button")
    public void i_click_on_the_checkin_button() {
        WebElement checkinButton = driver.findElement(By.id("checkin"));
        checkinButton.click();
    }


    //             Then I clinck on the "Get Ticket" button
    @Then("I click on the get ticket button")
    public void i_click_on_the_button() {
        WebElement getTicketButton = driver.findElement(By.id("getTicket"));
        getTicketButton.click();
    }





}
