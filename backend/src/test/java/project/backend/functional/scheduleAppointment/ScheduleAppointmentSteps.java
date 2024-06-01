package project.backend.functional.scheduleAppointment;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScheduleAppointmentSteps {


    private WebDriver driver;
    private JavascriptExecutor js;


    @When("I go to {string}")
    public void i_go_to(String url) {
        initializeDriver();
        driver.get(url);
    }

    @When("I insert Patient Number {string}")
    public void i_insert_patient_number(String patientNumber) {
        initializeDriver();
        WebElement patientNumberElement = driver.findElement(By.id(":r0:"));
        patientNumberElement.sendKeys(patientNumber);
    }

    @When("I insert Patient Name {string}")
    public void i_insert_patient_name(String patientName) {
        initializeDriver();
        WebElement patientNameElement = driver.findElement(By.id(":r1:"));
        patientNameElement.sendKeys(patientName);
    }

    @When("I insert Email {string}")
    public void i_insert_email(String email) {
        initializeDriver();
        WebElement emailElement = driver.findElement(By.id(":r2:"));
        emailElement.sendKeys(email);
    }

    @When("I select HealthCare Unit {string}")
    public void i_select_healthcare_unit(String healthcareUnit) {
        initializeDriver();
        WebElement healthcareUnitElement = driver.findElement(By.cssSelector("#mui-component-select-healthcareUnit > .notranslate"));
        healthcareUnitElement.click();
        WebElement healthcareUnitOption = driver.findElement(By.xpath("//li[text()='" + healthcareUnit + "']"));
        healthcareUnitOption.click();
    }

    @When("I select Specialty {string}")
    public void i_select_specialty(String specialty) {
        initializeDriver();
        WebElement specialtyElement = driver.findElement(By.cssSelector("#mui-component-select-medicalSpeciality > .notranslate"));
        specialtyElement.click();
        WebElement specialtyOption = driver.findElement(By.xpath("//li[text()='" + specialty + "']"));
        specialtyOption.click();
    }

    @When("I click on the search times button")
    public void i_click_on_the_search_times_button() {
        initializeDriver();
        WebElement searchButton = driver.findElement(By.cssSelector(".MuiButton-root"));
        System.out.println("Attempting to click the search button");
        searchButton.click();
        System.out.println("Clicked the search button");
    }

    @Then("I should go to {string}")
    public void i_should_go_to(String url) {
        initializeDriver();
        System.out.println("Waiting for URL to be " + url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe(url));
        System.out.println("URL is now " + driver.getCurrentUrl());
        assertTrue(driver.getCurrentUrl().equals(url));
    }


    // 2 

    @When("I choose date {string}")
    public void i_choose_date(String date) {
        initializeDriver();
        WebElement dateElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".Mui-selected")));
        dateElement.click();
        System.out.println("Chose date: " + date);
    }

    @When("I choose slot {string}")
    public void i_choose_slot(String slot) {
        initializeDriver();
        System.out.println("Attempting to choose slot: " + slot);
        WebElement slotElement = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//h6[contains(text(), '" + slot + "')]")));
        js.executeScript("arguments[0].click();", slotElement);
        System.out.println("Chose slot: " + slot);
    }

    @When("I click on the schedule appointment button")
    public void i_click_on_the_schedule_appointment_button() {
        initializeDriver();
        WebElement scheduleButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".MuiButton-root")));
        System.out.println("Attempting to click the schedule button");
    
        // Scroll to the button to ensure it's in view and not obscured
        js.executeScript("arguments[0].scrollIntoView(true);", scheduleButton);
        js.executeScript("window.scrollBy(0, -100);"); // Adjust if necessary to avoid any fixed headers
    
        // Wait a bit to ensure any animations or scrolling is completed
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(scheduleButton));
    
        // Click the button using JavaScript to avoid interception issues
        js.executeScript("arguments[0].click();", scheduleButton);
        System.out.println("Clicked the schedule button");
    }
    

    @Then("I should go to home {string}")
    public void i_should_go_to_home(String url) {
        initializeDriver();
        System.out.println("Waiting for URL to be " + url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe(url));
        System.out.println("URL is now " + driver.getCurrentUrl());
        assertTrue(driver.getCurrentUrl().equals(url));
        driver.quit();
    }

    private void initializeDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            js = (JavascriptExecutor) driver;
        }
    }
}
